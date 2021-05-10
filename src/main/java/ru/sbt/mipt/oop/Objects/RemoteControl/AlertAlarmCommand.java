package ru.sbt.mipt.oop.Objects.RemoteControl;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;

public class AlertAlarmCommand implements Command {
    private final SmartAlarm alarm;

    public AlertAlarmCommand(SmartAlarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.alert();
    }
}
