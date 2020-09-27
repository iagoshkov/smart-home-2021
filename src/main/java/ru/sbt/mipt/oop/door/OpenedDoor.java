package ru.sbt.mipt.oop.door;

public class OpenedDoor {

    Door door;

    public OpenedDoor(Door door) {
        this.door = new Door(true, door.getId());
    }

    public Door openedDoor(){
        return door;
    }
}
