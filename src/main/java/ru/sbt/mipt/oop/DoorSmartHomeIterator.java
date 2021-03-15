package ru.sbt.mipt.oop;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoorSmartHomeIterator implements Iterator<Door> {

    private final Iterator<Room> roomIterator;
    private Iterator<Door> doorIterator;

    DoorSmartHomeIterator(SmartHome smartHome) {
        roomIterator = smartHome.getRooms().iterator();
        doorIterator = roomIterator.next().getDoors().iterator();
    }

    @Override
    public boolean hasNext() {
        if (!doorIterator.hasNext()) {
            if (!roomIterator.hasNext()) {
                return false;
            } else {
                doorIterator = roomIterator.next().getDoors().iterator();
                return hasNext();
            }
        }
        return true;
    }

    @Override
    public Door next() {
        if (!doorIterator.hasNext()) {
            if (!roomIterator.hasNext()) {
                throw new NoSuchElementException();
            } else {
                doorIterator = roomIterator.next().getDoors().iterator();
                return next();
            }
        }
        return doorIterator.next();
    }

}
