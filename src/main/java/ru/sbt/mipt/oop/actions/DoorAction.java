package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.HomeComponent;

public class DoorAction implements Action {
    private boolean shouldOpen;

    public DoorAction(boolean shouldOpen) {
        this.shouldOpen = true;
    }

    public boolean isShouldOpen() {
        return shouldOpen;
    }

    public void setShouldOpen(boolean shouldOpen) {
        this.shouldOpen = shouldOpen;
    }

    @Override
    public void execute(HomeComponent component) {
        if (component instanceof Door) {
            ((Door) component).setActive(shouldOpen);
            System.out.println("Door " + component.getId() + " was " + (shouldOpen ? "open" : "closed"));
        }
    }

    @Override
    public ActionType getType() {
        return ActionType.DOOR;
    }
}
