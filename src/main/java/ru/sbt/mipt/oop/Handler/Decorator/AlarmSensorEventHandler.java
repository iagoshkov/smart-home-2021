package ru.sbt.mipt.oop.Handler.Decorator;
import java.util.List; 
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Handler.SensorEventHandler;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;

public class AlarmSensorEventHandler implements SensorEventHandler {
    private final SmartAlarm alarm;
    private final List<SensorEventHandler> handlers;

    public AlarmSensorEventHandler(SmartAlarm alarm, List<SensorEventHandler> handlers) {
        this.alarm = alarm;
        this.handlers = handlers;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (alarm.isActivated()) {
            alarm.alert();
            System.out.println("Sending sms");
        } else if (alarm.isDeactivated()) {
            for (SensorEventHandler handler : handlers) {
                handler.handleEvent(event);
            }
        }
    }
}
