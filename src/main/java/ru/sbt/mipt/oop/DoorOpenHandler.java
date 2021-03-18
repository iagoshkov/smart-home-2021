package ru.sbt.mipt.oop;

public class DoorOpenHandler implements Action {

    private final String doorId;

    public DoorOpenHandler(SensorEvent event) {
        this.doorId = event.getObjectId();
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Room room) {
            room.execute(new OpenCloseDoorAction(doorId, true));
            System.out.println("Door " + doorId + " in room " + room.getName() + " was opened.");
        }
    }

}
