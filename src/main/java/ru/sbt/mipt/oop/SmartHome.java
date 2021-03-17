package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeComponent, Actionable{
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    @Override
    public void execute(Action action){
        for (Room room:rooms){
            room.execute(action);
        }
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }
}
