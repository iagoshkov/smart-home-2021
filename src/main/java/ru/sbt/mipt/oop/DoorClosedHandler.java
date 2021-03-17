package ru.sbt.mipt.oop;

public class DoorClosedHandler implements Action {

    private final String doorId;

    public DoorClosedHandler(SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) {
            throw new IllegalArgumentException("The event type must be DOOR_CLOSED. Given: " + event.getType());
        }
        this.doorId = event.getObjectId();
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Room room) {
            room.execute(new OpenCloseDoorAction(doorId, false));
            System.out.println("Door " + doorId + " in room " + room.getName() + " was closed.");
        }
    }

}
