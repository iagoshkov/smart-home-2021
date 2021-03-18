package ru.sbt.mipt.oop;

import java.util.Collection;

public class SmartHome implements Actionable{
    private final Collection<Room> rooms;

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        rooms.forEach(room -> room.execute(action));
    }
}
