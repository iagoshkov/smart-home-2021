package ru.sbt.mipt.oop.door;

public class ClosedDoor {
    Door door;

    public ClosedDoor(Door door) {
        this.door = new Door(true, door.getId());
    }

    public Door closedDoor(){
        return door;
    }

}
