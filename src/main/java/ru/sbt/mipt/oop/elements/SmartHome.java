package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.ActionType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class SmartHome implements HomeComponentComposite {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }


    @Override
    public void addHomeComponent(ElementType type, HomeComponent component) {
        if (type == HomeElementType.ROOM) {
            rooms.add((Room) component);
        }
    }

    @Override
    public Collection<? extends HomeComponent> getComponents(ElementType type) {
        if (type == HomeElementType.ROOM) {
            return rooms;
        }
        return rooms.stream()
                .map((HomeComponent r) -> ((Room)r).getComponents(type))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public HomeComponent getComponent(ElementType type, ComponentId id) {
        if (type == HomeElementType.ROOM) {
            return rooms.stream()
                    .filter((HomeComponent c) -> (c.getId().equals(id)))
                    .findFirst()
                    .orElse(null);
        }
        return getComponents(type).stream()
                .filter((HomeComponent c) -> (c.getId().equals(id)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ElementType getType() {
        return HomeElementType.SMART_HOME;
    }

    @Override
    public ComponentId getId() {
        return new StringId("HOME");
    }

    public Action apply(Action action, ComponentId component) {
        Action newAction = rooms.stream()
                .map((HomeComponent r) -> r.apply(action, component))
                .filter((Action a) -> (a.getType().equals(ActionType.HALL)))
                .findFirst().orElse(null);
        return newAction;
    }
}
