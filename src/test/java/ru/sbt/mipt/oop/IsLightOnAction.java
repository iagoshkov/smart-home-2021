package ru.sbt.mipt.oop;

public class IsLightOnAction implements Action {

    private final String id;
    private Boolean isOn = null;

    public Boolean isOn() {
        return isOn;
    }

    public IsLightOnAction(String id) {
        this.id = id;
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Light light && light.getId().equals(id)) {
            isOn = light.isOn();
        }
    }

}
