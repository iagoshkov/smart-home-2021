package ru.sbt.mipt.oop.iterator;

import ru.sbt.mipt.oop.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SmartHomeIterator implements Iterable<Location> {
    private final SmartHome smartHome;
    private final List<Location<?>> locations = buildLocations();
    private int currentPosition = 0;

    private List<Location<?>> buildLocations() {
        List<Location<?>> locations = new ArrayList<>();
        if (smartHome == null) {
            return locations;
        }
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                locations.add(new Location<>(room, light));
            }
            for (Door door : room.getDoors()) {
                locations.add(new Location<>(room, door));
            }
        }
        return locations;
    }


    public SmartHomeIterator(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public Location getNext() {
        if (hasNext()) {
            currentPosition++;
            return locations.get(currentPosition - 1);
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        return currentPosition < locations.size();
    }

    @Override
    public void forEach(Consumer<Location> func){
        while (hasNext()) {
            func.accept(getNext());
        }
    }
}
