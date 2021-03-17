package ru.sbt.mipt.oop;

public class TurnLightOnOffAction implements Action {

    private final String id;
    private final boolean on;

    public TurnLightOnOffAction(String id, boolean on) {
        this.id = id;
        this.on = on;
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Light light) {
            if (light.getId().equals(id)) {
                light.setOn(on);
            }
        }
    }

}
