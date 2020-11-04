package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.Actionable;

import java.util.ArrayList;
import java.util.Collection;

public class Room implements HomeComponent {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        lights.forEach(light -> light.execute(action));
        doors.forEach(door -> door.execute(action));
    }

    @Override
    public String getId() {
        return null;
    }
}
