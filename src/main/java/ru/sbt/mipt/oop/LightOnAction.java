package ru.sbt.mipt.oop;

public class LightOnAction implements Action {

    @Override
    public void apply(Object obj) {
        if (obj instanceof Light light) {
            light.setOn(true);
        }
    }

}
