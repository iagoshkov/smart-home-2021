package ru.sbt.mipt.oop.objects;

public class Door extends SmartHomeObject {
    public Door(boolean isActive, String id) {
        super(id, isActive, SmartHomeObjectType.Door);
    }
}
