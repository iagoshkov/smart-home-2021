package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.events.actions.Action;

public class Light implements SmartDevice, Actionable {
    private final String id;
    private boolean isOn;

    public Light(String id, boolean isOn) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException();

        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void setOn(boolean on) {
        this.isOn = on;
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