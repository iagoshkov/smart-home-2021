package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BinaryOperator;

public class Room {
    public static class RoomBuilder {
        private String name;
        private Collection<Light> lights = new LinkedList<>();
        private Collection<Door> doors = new LinkedList<>();

        public RoomBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RoomBuilder withLights(Light... lights) {
            this.withLights(Arrays.asList(lights));
            return this;
        }

        public RoomBuilder withLights(Collection<Light> lights) {
            this.lights.addAll(lights);
            return this;
        }

        public RoomBuilder withDoors(Door... doors) {
            this.withDoors(Arrays.asList(doors));
            return this;
        }

        public RoomBuilder withDoors(Collection<Door> doors) {
            this.doors.addAll(doors);
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }

    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Room(RoomBuilder builder) {
        this.lights = builder.lights;
        this.doors = builder.doors;
        this.name = builder.name;
    }

    public Door getDoor(String id) {
        return doors.stream().filter((Door d) -> id.equals(d.getId())).findFirst().orElse(null);
    }

    public Light getLight(String id) {
        return lights.stream().filter((Light l) -> id.equals(l.getId())).findFirst().orElse(null);
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public void changeDoor(String id, boolean isOpen) {
        if (getDoor(id) != null) {
            getDoor(id).setOpen(isOpen);
            System.out.println("Door " + id + " in room " + this.name + (isOpen ? " was opened." : " was closed."));
        } else {
            System.out.println("No door with id " + id);
        }
    }

    public void changeLight(String id, boolean isOn) {
        if (getLight(id) != null) {
            getLight(id).setOn(isOn);
            System.out.println("Light " + id + " in room " + this.getName() + (isOn ? " was turned on." : " was turned off."));
        } else {
            System.out.println("No light with id " + id);
        }
    }

    public String getName() {
        return name;
    }


}
