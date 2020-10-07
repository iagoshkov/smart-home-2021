package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.Actionable;

import java.util.ArrayList;
import java.util.Collection;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<HomeComponent> getComponents() {
        Collection<HomeComponent> components = new ArrayList<>();

        for(HomeComponent light : lights) {
            components.add(light);
        }
        for(HomeComponent door : doors) {
            components.add(door);
        }
        return components;
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
    public void execute(Action action) {
        for (HomeComponent component : getComponents()) {
            action.act(component);
        }
    }
}
