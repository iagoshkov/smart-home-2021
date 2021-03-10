package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.SmartHomeReader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.SmartHomeReader.SmartHomeReader;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) {
        JsonSmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = smartHomeReader.read();

        listenToEvents(smartHome);
    }

    private static void listenToEvents(SmartHome smartHome) {
        for (SensorEvent event = getNextSensorEvent(); event != null; event = getNextSensorEvent()) {
            System.out.println("Got event: " + event);

            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                // событие от источника света
                for (Room room : smartHome.getRooms()) {
                    for (Light light : room.getLights()) {
                        if (light.getId().equals(event.getObjectId())) {
                            if (event.getType() == LIGHT_ON) {
                                light.setOn(true);
                                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                            } else {
                                light.setOn(false);
                                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                            }
                        }
                    }
                }
            }

            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                // событие от двери
                for (Room room : smartHome.getRooms()) {
                    for (Door door : room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
                            if (event.getType() == DOOR_OPEN) {
                                door.setOpen(true);
                                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                            } else {
                                door.setOpen(false);
                                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");

                                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                                if (room.getName().equals(smartHome.getHallRoomName())) {
                                    for (Room homeRoom : smartHome.getRooms()) {
                                        for (Light light : homeRoom.getLights()) {
                                            light.setOn(false);
                                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                            sendCommand(command);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
