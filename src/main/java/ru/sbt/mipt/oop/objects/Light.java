package ru.sbt.mipt.oop.objects;

public class Light extends SmartHomeObject{
    public Light(String id, boolean isActive) {
        super(id, isActive, SmartHomeObjectType.Light);
    }
}
