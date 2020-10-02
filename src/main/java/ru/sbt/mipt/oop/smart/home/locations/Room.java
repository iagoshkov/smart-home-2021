package ru.sbt.mipt.oop.smart.home.locations;

public class Room implements Location {
    private final String name;

    public Room(String name) {
        this.name = name;
    }

    @Override
    public String getLocationName() {
        return this.name;
    }
}