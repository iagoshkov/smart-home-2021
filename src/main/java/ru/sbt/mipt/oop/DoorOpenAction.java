package ru.sbt.mipt.oop;

public class DoorOpenAction implements Action {

    private final String doorId;

    public DoorOpenAction(SensorEvent event) {
        this.doorId = event.getObjectId();
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Room room) {
            room.execute(element -> {
                if (element instanceof Door door) {
                    if (door.getId().equals(doorId)) {
                        door.setOpen(true);
                        System.out.println("Door " + doorId + " in room " + room.getName() + " was opened.");
                    }
                }
            });
        }
    }

}