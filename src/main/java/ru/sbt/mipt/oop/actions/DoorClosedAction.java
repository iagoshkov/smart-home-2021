package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.door.Door;

public class DoorClosedAction implements Action {
    private final String doorObjectID;

    public DoorClosedAction(String id) {
        this.doorObjectID = id;
    }

    @Override
    public void accept(Actionable actionable) {
        if (actionable instanceof Door) {
            if (((Door) actionable).getId().equals(doorObjectID)) {
                ((Door) actionable).setOpen(false);
                System.out.println("Door " + doorObjectID + " was closed.");
            }
        }
    }
}
