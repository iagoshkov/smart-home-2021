package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
public class TurnOnAlertCommand implements RemoteControlCommand {

    private final SmartHome smartHome;

    public TurnOnAlertCommand(SmartHome smartHome) {

        this.smartHome = smartHome;
    }

    @Override
    public void execute() {

        smartHome.getAlarm().alert();
    }
}
