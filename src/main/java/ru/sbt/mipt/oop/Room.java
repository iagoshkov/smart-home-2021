package ru.sbt.mipt.oop;

import java.util.HashMap;

public class Room {
    private HashMap<String, Light> lights;
    private HashMap<String, Door> doors;
    private String name;

    public Room(HashMap<String, Light> lights, HashMap<String, Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public HashMap<String, Light> getLights() {
        return lights;
    }

    public HashMap<String, Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }
}
