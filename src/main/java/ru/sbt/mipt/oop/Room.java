package ru.sbt.mipt.oop;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Room {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    void processEvent(SensorEvent event) {
        sendEventToDoors(event);
        sendEventToLights(event);
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

    private void sendEventToLights(SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            for (Light light : lights) {
                light.processEvent(event);
            }
        }
    }

    private void sendEventToDoors(SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            for (Door door : doors) {
                door.processEvent(event);
            }
        }
    }

    Door findDoorById(String id) {
        for (Door door : doors) {
            if (door.getId().equals(id)) {
                return door;
            }
        }
        return null;
    }

    void turnAOffAllLightCommand() {
        for (Light light : lights) {
            light.turnOffCommand();
        }
    }
}
