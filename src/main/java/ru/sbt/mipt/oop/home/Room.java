package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.event_handlers.DoorEventHandler;
import ru.sbt.mipt.oop.event_handlers.LightEventHandler;
import ru.sbt.mipt.oop.event_handlers.SensorEvent;
import ru.sbt.mipt.oop.light.Light;

import java.util.Collection;

public class Room {

    private final Collection<Light> lights;
    private final Collection<Door> doors;
    private final String name;
    private final LightEventHandler lightEventHandler = new LightEventHandler();
    private final DoorEventHandler doorEventHandler = new DoorEventHandler();

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public void handleLightEvent(SensorEvent event, SmartHome smartHome) {
        lightEventHandler.handleLightEvent(event, this);
    }

    public void handleDoorEvent(SensorEvent event, SmartHome smartHome) {
        doorEventHandler.handleDoorEvent(event, smartHome, this);
    }
}
