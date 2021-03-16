package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class EventHandler {
    public static void EventLoop(SmartHome smartHome, SensorEvent event) {
        while (event != null) {
            System.out.println("Got event: " + event);


            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                lightEvent(smartHome, event);
            }

            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                doorEvent(smartHome, event);
            }

            event = getNextSensorEvent();
        }
    }
    private static void lightEvent(SmartHome smartHome, SensorEvent event) {
        // событие от источника света
        for (Room room : smartHome.getRooms().values()) {
            for (Light light : room.getLights().values()) {
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

    private static void doorEvent(SmartHome smartHome, SensorEvent event) {
        // событие от двери
        for (Room room : smartHome.getRooms().values()) {
            for (Door door : room.getDoors().values()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room instanceof Hall) {
                            turnOffEverything(smartHome);
                        }
                    }
                }
            }
        }
    }

    private static void turnOffEverything(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms().values()) {
            for (Light light : homeRoom.getLights().values()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                SensorCommander.sendCommand(command);
            }
        }
    }

    public static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
