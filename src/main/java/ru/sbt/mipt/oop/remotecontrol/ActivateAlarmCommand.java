package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;

public class ActivateAlarmCommand implements RemoteControlCommand {

    private SmartHome smartHome;
    private String code;

    public ActivateAlarmCommand(SmartHome smartHome, String code) {

        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void execute() {

        smartHome.getAlarm().activate(code);
    }
}

