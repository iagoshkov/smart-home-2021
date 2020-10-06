package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;

public class LightAction implements Action {
    private boolean shouldOn;

    public LightAction(boolean shouldOn) {
        this.shouldOn = true;
    }

    public boolean isShouldOn() {
        return shouldOn;
    }

    public void setShouldOn(boolean shouldOn) {
        this.shouldOn = shouldOn;
    }

    @Override
    public void execute(HomeComponent component) {
        if (component instanceof Light) {
            ((Light) component).setActive(shouldOn);
            System.out.println("Light " + component.getId() + " was " + (shouldOn ? "on" : "off"));
        }
    }


    @Override
    public ActionType getType() {
        return ActionType.LIGHT;
    }
}
