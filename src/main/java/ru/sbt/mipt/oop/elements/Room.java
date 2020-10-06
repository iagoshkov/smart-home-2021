package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.*;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.HallDoorEvent;
import ru.sbt.mipt.oop.events.typedefs.DoorEventType;
import ru.sbt.mipt.oop.events.typedefs.HallDoorEventType;
import ru.sbt.mipt.oop.events.typedefs.LightEventType;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Room implements HomeComponent, HomeComponentComposite {

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

    public int getElementCount(ElementType type) {
        if (type == HomeElementType.DOOR) {
            return ( doors == null ? 0 : doors.size());
        }
        if (type == HomeElementType.LIGHT) {
            return ( lights == null ? 0 : lights.size());
        }
        return 0;
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
        return new StringId("Room " + name);
    }

    @Override
    public Event apply(Event event, Action action) {
        Event inputEvent = event;
        if (inputEvent.getType() instanceof DoorEventType) {
            Collection<Door> doorsWithId = doors.stream()
                    .filter((HomeComponent c) -> (c.getId().equals(inputEvent.getObjectId())))
                    .collect(Collectors.toList());
            boolean hasDoor = (doorsWithId.size() > 0);
            if (hasDoor) {
                doorsWithId.forEach((Door d) -> d.apply(inputEvent, action));
                if (this.name.equals("hall") && (event.getType() == DoorEventType.DOOR_CLOSED)) {
                    event = new HallDoorEvent(HallDoorEventType.LIGHTS_OFF, this.getId(), new SimpleSensorCommand(CommandType.LIGHT_OFF, getId()));
                }
            }
        } else if (event.getType() instanceof LightEventType) {
            lights.stream().filter((HomeComponent c) -> (c.getId().equals(inputEvent.getObjectId()))).forEach(action);
        } else if (event.getType() instanceof HallDoorEventType) {
            lights.forEach((HomeComponent c) -> c.apply(inputEvent, action));
        }
        return event;
    }
}
