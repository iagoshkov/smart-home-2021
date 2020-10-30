package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public class AlarmEventHandler implements GeneralEvent{

    public AlarmEventHandler() {}

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            smartHome.getAlarm().activate(event.getCode());
        }
        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            smartHome.getAlarm().deactivate(event.getCode());
        }
    }
}
