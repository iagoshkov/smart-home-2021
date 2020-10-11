package ru.sbt.mipt.oop.events.actions;

import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public class ActionDecorator implements Action {
    private final boolean execute;
    private final Action action;

    public ActionDecorator(boolean execute, Action action) {
        this.execute = execute;
        this.action = action;
    }

    @Override
    public String getDeviceId() {
        return action.getDeviceId();
    }

    @Override
    public void act(SmartDevice smartDevice) {
        if (execute) action.act(smartDevice);
    }
}
