package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.HomeComponent;

public class DoorCloseAction implements Action {
    private final String objectId;

    public DoorCloseAction(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public void act(HomeComponent homeComponent) {
        if (homeComponent instanceof Door) {
            if (homeComponent.getId().equals(objectId)) {
                Door door = (Door) homeComponent;
                door.setClosed();
                System.out.println("Door " + door.getId() + " was closed.");
            }
        }
    }
}
