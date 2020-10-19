package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.event_handlers.DoorEventHandler;
import ru.sbt.mipt.oop.event_handlers.LightEventHandler;
import ru.sbt.mipt.oop.event_handlers.SensorEvent;
import ru.sbt.mipt.oop.light.Light;

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

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public void handleLightEvent(SensorEvent event) {
        for (Light light : this.getLights()) {
            if (light.getId().equals(event.getObjectId())) {
                LightEventHandler lightEventHandler = new LightEventHandler(event, light);
                lightEventHandler.handleLightEvent();
                if (event.getType().toString().equals("LIGHT_ON")) {
                    System.out.println("Light " + light.getId() + " in room " + this.getName() + " was turned on.");
                } else {
                    System.out.println("Light " + light.getId() + " in room " + this.getName() + " was turned off.");
                }
            }
        }
    }

    public void handleDoorEvent(SensorEvent event) {
        for (Door door:this.getDoors()){
            if (door.getId().equals(event.getObjectId())){
                    DoorEventHandler doorEventHandler = new DoorEventHandler(event, door);
                    doorEventHandler.handleDoorEvent();
                    if (event.getType().toString().equals("DOOR_OPEN")) {
                        System.out.println("Door " + door.getId() + " in room " + this.getName() + " was opened.");
                    } else {
                        System.out.println("Door " + door.getId() + " in room " + this.getName() + " was closed.");
                    }
            }
        }
    }

}
