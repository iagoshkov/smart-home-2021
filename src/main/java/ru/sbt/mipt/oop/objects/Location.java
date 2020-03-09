package ru.sbt.mipt.oop.objects;

public class Location {
    private final Room room;
    private final SmartHomeObject smartHomeObject;

    public Location(Room room, SmartHomeObject object) {
        this.room = room;
        this.smartHomeObject = object;
    }

    public SmartHomeObject getObject() {
        return smartHomeObject;
    }

    public Room getRoom() {
        return room;
    }
}
