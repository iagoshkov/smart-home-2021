package ru.sbt.mipt.oop.objects;

public abstract class SmartHomeObject {
    private final String id;
    private boolean isActive;
    private SmartHomeObjectType type;

    public SmartHomeObject(String id, boolean isActive, SmartHomeObjectType type) {
        this.isActive = isActive;
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public SmartHomeObjectType getType() {
        return type;
    }
}
