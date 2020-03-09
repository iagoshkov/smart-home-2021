package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.iterator.SmartHomeIterator;
import ru.sbt.mipt.oop.iterator.Iterator;


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

    public Location findLightByID(String id) {
        Iterator<Location> iterator = new SmartHomeIterator(this);
        while (iterator.hasNext()) {
            Location currentLocation = iterator.getNext();
            if (currentLocation.getObject().getId().equals(id) && currentLocation.getObject().getType() == SmartHomeObjectType.Light) {
                return currentLocation;
            }
        }
        return null;
    }

    public Location findDoorByID(String id) {
        Iterator<Location> iterator = new SmartHomeIterator(this);
        while (iterator.hasNext()) {
            Location currentLocation = iterator.getNext();
            if (currentLocation.getObject().getId().equals(id) && currentLocation.getObject().getType() == SmartHomeObjectType.Door) {
                return currentLocation;
            }
        }
        return null;
    }

}
