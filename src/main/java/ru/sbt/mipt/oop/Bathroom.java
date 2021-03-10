package ru.sbt.mipt.oop;

import java.util.HashMap;

public class Bathroom extends Room{
    public Bathroom(HashMap<String, Light> lights, HashMap<String, Door> doors, String name) {
        super(lights, doors, name);
    }
}
