package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.components.Door;

public class DoorOpenAction implements Action{
    private final String objectId;

    public DoorOpenAction(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public void act(Actionable actionable) {
        if(actionable instanceof Door) {
            Door door = (Door) actionable;
            if (door.getId().equals(objectId)) {
                door.setOpen();
                System.out.println("Door " + door.getId() + " was opened.");
            }
        }
    }
}
