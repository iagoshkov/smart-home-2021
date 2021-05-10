package ru.sbt.mipt.oop.Handler;
import ru.sbt.mipt.oop.Event.*;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;
import static ru.sbt.mipt.oop.Type.EventType.ON;
import static ru.sbt.mipt.oop.Types.EventType.OFF;

public class AlarmEventHandler implements SensorEventHandler{
    private SmartAlarm alarm;

    public AlarmEventHandler(SmartAlarm alarm) {
        this.alarm = alarm; 
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event instanceof AlarmEvent) {
            if (((AlarmEvent)event).getAlarmEventType() == ON) {
                alarm.activate(((AlarmEvent)event).getCode());
            } else if (((AlarmEvent)event).getAlarmEventType() == OFF) {
                alarm.deactivate(((AlarmEvent)event).getCode());
            }
        }
    }
}
