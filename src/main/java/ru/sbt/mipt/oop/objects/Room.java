package ru.sbt.mipt.oop.objects;

import java.util.Collection;

public class Room {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private final String name;

    public Room(String name) {
        this.name = name;
    }

    public void addDoors(Collection <Door> doors) {
        this.doors = doors;
    }

    public void addLights(Collection <Light> lights) {
        this.lights = lights;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }
}
