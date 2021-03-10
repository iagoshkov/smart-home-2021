package ru.sbt.mipt.oop;

import java.util.HashMap;

public class Kitchen extends Room{
    public Kitchen(HashMap<String, Light> lights, HashMap<String, Door> doors, String name) {
        super(lights, doors, name);
    }
}
