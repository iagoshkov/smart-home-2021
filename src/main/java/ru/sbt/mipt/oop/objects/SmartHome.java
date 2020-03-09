package ru.sbt.mipt.oop.objects;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome{
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

    public <T> Location<Light> findLightByID(String id) {
        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id)) {
                    return new Location<>(room, light);
                }
            }
        }
        return null;
    }

    public <T> Location<Door> findDoorByID(String id) {
        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    return new Location<>(room, door);
                }
            }
        }
        return null;
    }

}
