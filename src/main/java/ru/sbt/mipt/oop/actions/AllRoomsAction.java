package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Room;

import java.util.ArrayList;
import java.util.List;

public class AllRoomsAction implements Action {

    private final List<Room> rooms = new ArrayList<>();

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Room room) {
            rooms.add(room);
        }
    }

}
