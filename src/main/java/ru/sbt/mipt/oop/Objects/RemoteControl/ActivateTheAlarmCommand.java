package ru.sbt.mipt.oop.objects.RemoteControl;
import ru.sbt.mipt.oop.Event.AlarmEvent;
import ru.sbt.mipt.oop.Handler.AlarmEventHandler;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;
import ru.sbt.mipt.oop.Type.EventType;

public class ActivateTheAlarmCommand implements Command {
    private final SmartAlarm alarm;

    public ActivateTheAlarmCommand(SmartAlarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        String code = "default code";
        AlarmEvent event = new AlarmEvent(AlarmEventType.ON, code);
        AlarmEventHandler handler = new AlarmEventHandler(alarm);
        handler.handleEvent(event);
    }
}
