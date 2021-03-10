package ru.sbt.mipt.oop;

import java.util.HashMap;

public class Hall extends Room{
    public Hall(HashMap<String, Light> lights, HashMap<String, Door> doors, String name) {
        super(lights, doors, name);
    }
}
