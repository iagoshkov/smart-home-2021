package ru.sbt.mipt.oop;

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

            executeCommand(event.getType(), device);
        }
    }

    private static void executeCommand(SensorEventType eventType, SmartDevice device) {
        switch (eventType) {
            case LIGHT_ON:
                if (device.getType() != SmartDeviceType.LIGHT) {
                    System.out.printf("The device type '%s' does not match the event action '%s'%n", device.getType().toString(), eventType.toString());
                    break;
                }
                ((Light) device).setOn(true);
                System.out.println("Light " + device.getId() + " in room " + device.getLocation().getName() + " was turned on");
                break;

            case LIGHT_OFF:
                if (device.getType() != SmartDeviceType.LIGHT) {
                    System.out.printf("The device type '%s' does not match the event action '%s'%n", device.getType().toString(), eventType.toString());
                    break;
                }
                ((Light) device).setOn(false);
                System.out.println("Light " + device.getId() + " in room " + device.getLocation().getName() + " was turned off");
                break;

            case DOOR_OPEN:
                if (device.getType() != SmartDeviceType.DOOR) {
                    System.out.printf("The device type '%s' does not match the event action '%s'%n", device.getType().toString(), eventType.toString());
                    break;
                }
                ((Door) device).setOpen(true);
                System.out.println("Door " + device.getId() + " in room " + device.getLocation().getName() + " was opened");
                break;

            case DOOR_CLOSED:
                if (device.getType() != SmartDeviceType.DOOR) {
                    System.out.printf("The device type '%s' does not match the event action '%s'%n", device.getType().toString(), eventType.toString());
                    break;
                }
                ((Door) device).setOpen(false);
                String roomName = device.getLocation().getName();
                System.out.println("Door " + device.getId() + " in room " + roomName + " was closed");
                if (roomName.equals("hall")) {
                    smartHome.turnOffAllLights();
                    System.out.println("All the lights were off");
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
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
