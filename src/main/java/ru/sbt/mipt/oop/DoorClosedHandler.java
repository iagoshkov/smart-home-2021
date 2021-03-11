package ru.sbt.mipt.oop;

public class DoorClosedHandler implements EventHandler {

    @Override
    public CommandType handleEvent(Room room, Light light, Door door) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");

        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        String hallRoomName = "hall";
        if (room.getName().equals(hallRoomName)) {
            return CommandType.LIGHT_OFF;
        }
        return null;
    }

}
