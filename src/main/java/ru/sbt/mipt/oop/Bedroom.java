package ru.sbt.mipt.oop;

import java.util.HashMap;

public class Bedroom extends Room{
    public Bedroom(HashMap<String, Light> lights, HashMap<String, Door> doors, String name) {
        super(lights, doors, name);
    }
}
