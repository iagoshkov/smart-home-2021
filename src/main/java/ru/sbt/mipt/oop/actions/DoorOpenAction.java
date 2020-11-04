package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.HomeComponent;

public class DoorOpenAction implements Action{
    private final String objectId;

    public DoorOpenAction(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public void act(HomeComponent homeComponent) {
        if(homeComponent instanceof Door) {
            if (homeComponent.getId().equals(objectId)) {
                Door door = (Door) homeComponent;
                door.setOpen();
                System.out.println("Door " + door.getId() + " was opened.");
            }
        }
    }
}
