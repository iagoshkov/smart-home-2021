package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {

    Collection<Room> rooms;

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public String getHallDoorId(){
        return rooms
                .stream()
                .filter(x -> x.getName().equals("hall"))
                .findFirst()
                .get()
                .getDoors()
                .stream()
                .findFirst()
                .get()
                .getId();
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
        for (Room room : rooms){
            room.execute(action);
        }
    }
}
