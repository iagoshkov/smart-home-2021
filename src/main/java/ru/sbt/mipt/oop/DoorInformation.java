package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventTypeDoor.DOOR_OPEN;

public class DoorInformation {
    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
    public static void printDoor(Event event, Door door, SmartHome smartHome, Room room){
        if (event.getEvent().getTypeDoor() == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
            if (room.getName().equals("hall")) {
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
