package ru.sbt.mipt.oop;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

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
            light.handleEvent(event, this.getName());
        }
    }

    public boolean handleDoorEvent(SmartHome smartHome, SensorEvent event) {
        boolean _check = false;
        for (Door door : this.getDoors()) {
            boolean _check1 = door.handleEvent(smartHome, event, this.getName());
            if (_check1 == true){
                _check = _check1;
            }
        }
        return _check;
    }
}
