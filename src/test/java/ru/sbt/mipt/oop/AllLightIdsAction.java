package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class AllLightIdsAction implements Action {

    private final List<String> ids = new ArrayList<>();

    public List<String> getIds() {
        return ids;
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Light light) {
            ids.add(light.getId());
        }
    }

}
