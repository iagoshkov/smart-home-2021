package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class EventManager {
    SmartHome smartHome;

    public EventManager() {
    }

    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        if (event.isLightType()) {
            // событие от источника света
            executeLightEvent(event);
        }
        if (event.isDoorType()) {
            // событие от двери
            executeDoorEvent(event);
        }
    }

    private void executeDoorEvent(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen();
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    }
                    if (event.getType() == DOOR_CLOSED) {
                        door.setClosed();
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            lightOffAllRooms();
                        }
                    }
                }
            }
        }
    }

    private void executeLightEvent(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn();
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    }
                    if (event.getType() == LIGHT_OFF) {
                        light.setOff();
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }

    private void lightOffAllRooms() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOff();
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
