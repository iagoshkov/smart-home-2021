package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Light;

import java.util.ArrayList;
import java.util.List;

public class AllLightsAction implements Action {

    private final List<Light> lights = new ArrayList<>();

    public List<Light> getLights() {
        return lights;
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Light light) {
            lights.add(light);
        }
    }

}
