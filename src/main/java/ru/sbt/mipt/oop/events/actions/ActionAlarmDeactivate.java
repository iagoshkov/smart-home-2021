package ru.sbt.mipt.oop.events.actions;

import ru.sbt.mipt.oop.smart.devices.Alarm;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public class ActionAlarmDeactivate implements Action {
    private final String password;

    public ActionAlarmDeactivate(String password) {
        this.password = password;
    }

    @Override
    public String getDeviceId() {
        return null;
    }

    @Override
    public void act(SmartDevice smartDevice) {
        if (!(smartDevice instanceof Alarm)) return;
        ((Alarm)smartDevice).deactivate(password);
    }
}
