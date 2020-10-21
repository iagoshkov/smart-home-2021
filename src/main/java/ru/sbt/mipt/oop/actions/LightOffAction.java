package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.light.Light;

public class LightOffAction implements Action {
    private final Light lightObject;

    public LightOffAction(Light lightObject) {
        this.lightObject = lightObject;
    }

    @Override
    public void accept(Actionable actionable) {
        if (actionable instanceof Light) {
            if (((Light) actionable).getId().equals(lightObject.getId())) {
                ((Light) actionable).setOn(false);
                System.out.println("Light " + lightObject.getId() + " was turned off.");
            }
        }
    }
}
