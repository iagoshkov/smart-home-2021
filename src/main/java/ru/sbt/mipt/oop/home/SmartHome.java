package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.event_handlers.ClosedHallDoorEvent;
import ru.sbt.mipt.oop.event_handlers.SensorEvent;

import java.util.Collection;

public class SmartHome implements Actionable {

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


    @Override
    public void execute(Action action) {
        action.accept(this);
        for (Room room : rooms){
            room.execute(action);
        }
    }
}
