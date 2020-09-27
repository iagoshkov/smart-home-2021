package ru.sbt.mipt.oop.home;

import java.util.Collection;

public class SmartHome {

    Collection<Room> rooms;

    public Collection<Room> getRooms() {
        return rooms;
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

}
