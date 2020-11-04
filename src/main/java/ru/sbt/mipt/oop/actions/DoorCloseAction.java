package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.components.Door;

public class DoorCloseAction implements Action {
    private final String objectId;

    public DoorCloseAction(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public void act(Actionable actionable) {
        if (actionable instanceof Door) {
            Door door = (Door) actionable;
            if (door.getId().equals(objectId)) {
                door.setClosed();
                System.out.println("Door " + door.getId() + " was closed.");
            }
        }
    }
}
