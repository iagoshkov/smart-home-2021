package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private final String name;

    public Room(String name) {
        this.name = name;
    }

    public void addDoors(Collection <Door> doors) {
        this.doors = doors;
    }

    public void addLights(Collection <Light> lights) {
        this.lights = lights;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
        lights.forEach(light -> light.execute(action));
        doors.forEach(door -> door.execute(action));
    }
}
