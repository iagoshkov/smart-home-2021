package ru.sbt.mipt.oop;

public class DoorOpenHandler implements EventHandler {

    @Override
    public CommandType handleEvent(Room room, Light light, Door door) {
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        return null;
    }

}
