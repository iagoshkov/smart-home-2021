package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.event_handlers.ClosedHallDoorEvent;
import ru.sbt.mipt.oop.event_handlers.SensorEvent;

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
}
