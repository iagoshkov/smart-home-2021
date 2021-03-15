package ru.sbt.mipt.oop;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LightSmartHomeIterator implements Iterator<Light> {

    private final Iterator<Room> roomIterator;
    private Iterator<Light> lightIterator;

    LightSmartHomeIterator(SmartHome smartHome) {
        roomIterator = smartHome.getRooms().iterator();
        lightIterator = roomIterator.next().getLights().iterator();
    }

    @Override
    public boolean hasNext() {
        if (!lightIterator.hasNext()) {
            if (!roomIterator.hasNext()) {
                return false;
            } else {
                lightIterator = roomIterator.next().getLights().iterator();
                return hasNext();
            }
        }
        return true;
    }

    @Override
    public Light next() {
        if (!lightIterator.hasNext()) {
            if (!roomIterator.hasNext()) {
                throw new NoSuchElementException();
            } else {
                lightIterator = roomIterator.next().getLights().iterator();
                return next();
            }
        }
        return lightIterator.next();
    }

}
