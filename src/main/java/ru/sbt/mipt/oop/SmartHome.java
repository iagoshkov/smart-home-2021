package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    // Encapsulation
    private Collection<Room> rooms;
    // To remove magic constant
    private final String hallRoomName;

    public SmartHome() {
        this.rooms = new ArrayList<>();
        this.hallRoomName = "";
    }

    public SmartHome(Collection<Room> rooms, String hallRoomName) {
        this.rooms = rooms;
        this.hallRoomName = hallRoomName;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public String getHallRoomName() {
        return hallRoomName;
    }
}
