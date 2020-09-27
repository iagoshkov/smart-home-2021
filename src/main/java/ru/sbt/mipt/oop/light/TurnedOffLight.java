package ru.sbt.mipt.oop.light;

public class TurnedOffLight {
    Light light;

    public TurnedOffLight(Light light) {
        this.light = new Light(light.getId(), false);
    }

    public Light turnedOffLight(){
        return light;
    }
}
