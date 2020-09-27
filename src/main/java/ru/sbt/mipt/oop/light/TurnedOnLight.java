package ru.sbt.mipt.oop.light;

public class TurnedOnLight {

    Light light;

    public TurnedOnLight(Light light) {
        this.light = new Light(light.getId(), true);
    }

    public Light turnedOnLight(){
        return light;
    }
}
