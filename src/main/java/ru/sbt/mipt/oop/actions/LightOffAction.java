package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.components.Light;

public class LightOffAction implements Action{
    private final String objectId;

    public LightOffAction(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public void act(Actionable actionable) {
        if(actionable instanceof Light) {
            Light light = (Light) actionable;
            if (light.getId().equals(objectId)) {
                light.setOff();
                System.out.println("Light " + light.getId() + " was turned off.");
            }
        }
    }
}
