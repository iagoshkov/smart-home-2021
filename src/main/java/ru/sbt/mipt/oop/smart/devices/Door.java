package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.events.actions.Action;

public class Door implements SmartDevice, Actionable {
    private final String id;
    private boolean isOpen;

    public Door(String id, boolean isOpen) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException();

        this.id = id;
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void execute(Action action) {
        if (action == null) return;
        action.act(this);
    }
}