package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;

public class HallAction implements Action {

    private boolean lightsOn;

    public HallAction(boolean lightsOn) {
        this.lightsOn = lightsOn;
    }

    public boolean isLightsOn() {
        return lightsOn;
    }

    public void setLightsOn(boolean lightsOn) {
        this.lightsOn = lightsOn;
    }

    @Override
    public void execute(HomeComponent component) {
        if (component instanceof Light) {
            ((Light) component).setActive(lightsOn);
            System.out.println("Light " + component.getId() + " was " + (lightsOn ? "on" : "off"));
        }
    }

    @Override
    public ActionType getType() {
        return ActionType.HALL;
    }
}
