package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.Actionable;

import java.util.Collection;
import java.util.function.Consumer;

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

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Consumer<Object> action) {
        action.accept(this);
        lights.forEach(light -> light.execute(action));
        doors.forEach(door -> door.execute(action));
    }
}
