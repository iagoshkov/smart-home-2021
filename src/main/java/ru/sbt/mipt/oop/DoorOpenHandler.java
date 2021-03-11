package ru.sbt.mipt.oop;

public class DoorOpenHandler implements EventHandler {

    @Override
    public CommandType handleEvent(SensorEvent event, Room room, Light light, Door door) {
        if (event.getType() != SensorEventType.DOOR_OPEN ||
                !door.getId().equals(event.getObjectId())) return null;

        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        return null;
    }

}
