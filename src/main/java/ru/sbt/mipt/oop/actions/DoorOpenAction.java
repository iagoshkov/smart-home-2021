package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.door.Door;


public class DoorOpenAction implements Action {
    private final Door doorObject;

    public DoorOpenAction(Door doorObject) {
        this.doorObject = doorObject;
    }

    @Override
    public void accept(Actionable actionable) {
        if (actionable instanceof Door) {
            if (((Door) actionable).getId().equals(doorObject.getId())) {
                ((Door) actionable).setOpen(true);
                System.out.println("Door " + doorObject.getId() + " was opened.");
            }
        }
    }
}
