package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.HomeComponent;
import ru.sbt.mipt.oop.Light;

public class LightOnAction implements Action{
    @Override
    public void act(HomeComponent homeComponent) {
        if(homeComponent instanceof Light) {
            Light light = (Light) homeComponent;
            light.setOn();
            System.out.println("Light " + light.getId() + " was turned on.");
        }
    }
}
