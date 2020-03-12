package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.Actionable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void execute(Consumer<Object> action) {
        rooms.forEach(room -> room.execute(action));
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }
}
