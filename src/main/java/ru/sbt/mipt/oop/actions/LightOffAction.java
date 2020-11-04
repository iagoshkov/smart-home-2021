package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.components.HomeComponent;
import ru.sbt.mipt.oop.components.Light;

public class LightOffAction implements Action{
    private final String objectId;

    public LightOffAction(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public void act(HomeComponent homeComponent) {
        if(homeComponent instanceof Light) {
            if (homeComponent.getId().equals(objectId)) {
                Light light = (Light) homeComponent;
                light.setOff();
                System.out.println("Light " + light.getId() + " was turned off.");
            }
        }
    }
}
