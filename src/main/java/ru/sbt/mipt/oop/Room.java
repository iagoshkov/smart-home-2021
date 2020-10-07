package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.Actionable;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<HomeComponent> components;
    private String name;

    public Room(Collection<HomeComponent> components, String name) {
        this.components = components;
        this.name = name;
    }

    public Collection<HomeComponent> getComponents() {
        return components;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        for (HomeComponent component : components) {
            action.act(component);
        }
    }
}
