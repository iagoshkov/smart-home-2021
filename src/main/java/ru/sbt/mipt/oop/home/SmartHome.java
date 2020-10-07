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

    public void handleLightEvent(SensorEvent event) {
        for (Room room : this.getRooms()) {
            room.handleLightEvent(event);
        }
    }
    public void handleDoorEvent(SensorEvent event) {
        for (Room room : this.getRooms()) {
            if (room.getName().equals("hall")){
                ClosedHallDoorEvent closedHallDoorEvent = new ClosedHallDoorEvent();
                closedHallDoorEvent.hallDoorClosed(this);
                room.handleDoorEvent(event);
            } else {
                room.handleDoorEvent(event);
            }
        }
    }

    @Override
    public void execute(Action action) {
        rooms.forEach(room -> room.execute(action));
        action.accept(this);
    }
}
