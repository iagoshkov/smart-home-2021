package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    // Encapsulation
    private Collection<Room> rooms;

    public SmartHome() {
        this.rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Is it need?
    // public void removeRoom(String name)

    public Collection<Room> getRooms() {
        return rooms;
    }
}
