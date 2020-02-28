package ru.sbt.mipt.oop;

public class Location<T> {
    private final Room room;
    private final T object;

    public Location(Room room, T object) {
        this.room = room;
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public Room getRoom() {
        return room;
    }
}
