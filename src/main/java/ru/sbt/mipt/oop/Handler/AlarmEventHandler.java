package ru.sbt.mipt.oop.Handler;
import ru.sbt.mipt.oop.Event.*;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;
import static ru.sbt.mipt.oop.Type.AlarmEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.Types.AlarmEventType.ALARM_DEACTIVATE;

public class AlarmEventHandler implements SensorEventHandler{
    private SmartAlarm alarm;

    public AlarmEventHandler(SmartAlarm alarm) {
        this.alarm = alarm; 
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event instanceof AlarmEvent) {
            if (((AlarmEvent)event).getAlarmEventType() == ALARM_ACTIVATE) {
                alarm.activate(((AlarmEvent)event).getCode());
            } else if (((AlarmEvent)event).getAlarmEventType() == ALARM_DEACTIVATE) {
                alarm.deactivate(((AlarmEvent)event).getCode());
            }
        }
    }
}
