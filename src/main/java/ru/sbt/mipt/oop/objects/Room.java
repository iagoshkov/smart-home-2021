package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.Collection;

public class Room {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public void handleUsualEvent(SensorEvent event) {
        if (event.isLightUsualEvent()) {
            // events from Light
            for (Light light : lights) {
                if (light.getId().equals(event.getObjectId())) {
                    light.handleEvent(event, name);
                }
            }
        }
        if (event.isDoorUsualEvent()) {
            // events from Door
            for (Door door : doors) {
                if (door.getId().equals(event.getObjectId())) {
                    door.handleEvent(event, name);
                }
            }
        }
        // add usual events' handlers here
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
}
