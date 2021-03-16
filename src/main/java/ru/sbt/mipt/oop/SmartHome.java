package ru.sbt.mipt.oop;

import java.util.HashMap;

public class SmartHome {
    private final HashMap<String, Room> rooms;

    public SmartHome() {
        rooms = new HashMap<>();
    }

    public SmartHome(HashMap<String, Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.put(room.getName(), room);
    }

    public HashMap<String, Room> getRooms() {
        return rooms;
    }
}
