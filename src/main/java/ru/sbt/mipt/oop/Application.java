package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.SmartHomeReaderWriter;

import java.io.IOException;

public class Application {
    private static SmartHome smartHome;

    public static void main(String... args) {
        try {
            smartHome = SmartHomeReaderWriter.loadFromJson(Constants.INPUT_SMART_HOME_JSON_FILE_NAME);
        } catch (IOException e) {
            System.out.println("Failed to initialize smart home");
            e.printStackTrace();
        }

        run();
    }

    /**
     * Запуск цикла обработки событий
     */
    private static void run() {
        while (true) {
            SensorEvent event = getNextSensorEvent();
            if (event == null) return;

            System.out.println("Got event: " + event);

            SmartDevice device = smartHome.getDevice(event.getObjectId());
            if (device == null) {
                System.out.printf("Device id '%s' is not exist%n", event.getObjectId());
                continue;
            }

            event.getType().getAction().act(device);

            if (event.getType() == SensorEventType.DOOR_CLOSED && device.getLocation().getName().equals("hall")) {
                smartHome.turnOffAllLights();
                System.out.println("All the lights were off");
            }
        }
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];

        int offset = sensorEventType == SensorEventType.LIGHT_ON || sensorEventType == SensorEventType.LIGHT_OFF ? 0 : 5;
        String objectId = "" + ((int) (4 * Math.random()) + offset);
        return new SensorEvent(sensorEventType, objectId);
    }
}
