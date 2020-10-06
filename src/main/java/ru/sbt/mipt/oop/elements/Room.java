package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.*;

import java.util.Collection;
import java.util.Map;

public class Room implements HomeComponentComposite {

    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    @Override
    public void addHomeComponent(ElementType type, HomeComponent component) {
        if (type.equals(HomeElementType.DOOR)) {
            doors.add((Door)component);
        } else if (type.equals(HomeElementType.LIGHT)) {
            lights.add((Light)component);
        }
    }

    @Override
    public Collection<? extends HomeComponent> getComponents(ElementType type) {
        if (type.equals(HomeElementType.DOOR)) {
            return doors;
        } else if (type.equals(HomeElementType.LIGHT)) {
            return lights;
        }
        return null;
    }

    public Room(Map<ComponentId, Light> lights, Map<ComponentId, Door> doors, String name) {
        this.lights = lights.values();
        this.doors = doors.values();
        this.name = name;
    }

    public HomeComponent getComponent(ElementType type, ComponentId id) {
        if (type == HomeElementType.DOOR) {
            return doors.stream().filter((HomeComponent c) -> c.getId().equals(id)).findFirst().orElse(null);
        } else if (type == HomeElementType.LIGHT) {
            return lights.stream().filter((HomeComponent c) -> c.getId().equals(id)).findFirst().orElse(null);
        }
        return null;
    }

    public String getName() {
        return name;
    }


    @Override
    public ElementType getType() {
        return HomeElementType.ROOM;
    }

    @Override
    public ComponentId getId() {
        return new StringId("Room" + name);
    }

    @Override
    public Action apply(Action action, ComponentId component) {
        if (action.getType() == ActionType.DOOR) {
            doors.stream().filter((HomeComponent c) -> (c.getId().equals(component))).forEach(action::execute);
            if (this.name.equals("hall") && !((DoorAction) action).isShouldOpen()) {
                action = new HallAction(false);
            }
        } else if (action.getType() == ActionType.LIGHT) {
            lights.stream().filter((HomeComponent c) -> (c.getId().equals(component))).forEach(action::execute);
        } else if (action.getType() == ActionType.HALL) {
            Action finalAction = action;
            lights.stream().map((HomeComponent c) -> (c.apply(finalAction, this.getId())));
        }
        return action;
    }
}
