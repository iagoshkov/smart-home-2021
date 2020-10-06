package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.actions.HomeComponent;

public class Door implements HomeComponent {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setOpen() {
        isOpen = true;
    }

    public void setClosed() {
        isOpen = false;
    }
}
