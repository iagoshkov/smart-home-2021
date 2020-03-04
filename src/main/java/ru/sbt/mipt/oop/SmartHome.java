package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SmartHome {
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
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

    public void processEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        sendEventToRooms(event);
        processHallDoorClosedEvent(event);
    }

    private void sendEventToRooms(SensorEvent event) {
        for (Room room : rooms) {
            room.processEvent(event);
        }
    }

    private void processHallDoorClosedEvent(SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) {
            return;
        }
        Room hall = getRoomByName("hall");
        if (hall == null) {
            return;
        }
        Door door = hall.findDoorById(event.getObjectId());
        if (door == null) {
            return;
        }
        turnOffAllLightCommand();
    }

    private Room getRoomByName(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    private void turnOffAllLightCommand() {
        for (Room room : rooms) {
            room.turnAOffAllLightCommand();
        }
    }
}
