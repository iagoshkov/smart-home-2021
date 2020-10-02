package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.EventManager;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeJsonReaderWriter;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeReaderWriter;

public class Application {
    private final SmartHomeReaderWriter smartHomeReaderWriter;
    private final EventManager eventManager;
    private SmartHome smartHome;

    public Application(SmartHomeReaderWriter smartHomeReaderWriter, EventManager eventManager) {
        this.smartHomeReaderWriter = smartHomeReaderWriter;
        this.eventManager = eventManager;
    }

    public static void main(String... args) {
        SmartHomeReaderWriter smartHomeReaderWriter = new SmartHomeJsonReaderWriter(
                Constants.INPUT_SMART_HOME_JSON_FILE_NAME, Constants.OUTPUT_SMART_HOME_JSON_FILE_NAME);
        EventManager eventManager = new EventManager();
        Application application = new Application(smartHomeReaderWriter, eventManager);

        application.start();
    }

    public void start() {
        try {
            smartHome = smartHomeReaderWriter.loadSmartHome();
        } catch (RuntimeException e) {
            System.out.println("Failed to initialize smart home");
            e.printStackTrace();
            return;
        }
        run();
    }

    private void run() {
        while(true) {
            SensorEvent event = eventManager.getNextSensorEvent();
            if (event == null) return;

            System.out.println("Got event: " + event);
            SmartDevice device = smartHome.getDevice(event.getObjectId());

            if (device == null) {
                System.out.printf("Device id '%s' is not exist%n", event.getObjectId());
                continue;
            }

            eventManager.executeEvent(event.getType(), device);

            if (event.getType() == SensorEventType.DOOR_CLOSED && device.getLocation().getLocationName().equals("hall")) {
                eventManager.executeEvent(SensorEventType.TURNOFF_ALL_LIGHTS_IN_SMART_HOME, smartHome);
            }
        }
    }
}