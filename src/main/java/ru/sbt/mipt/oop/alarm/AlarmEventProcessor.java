package ru.sbt.mipt.oop.alarm;

public class AlarmEventProcessor {
    public void processEvent(Alarm alarmSystem, AlarmEvent event) {
        switch (event.type()) {
            case ALARM_ACTIVATE -> alarmSystem.activate(event.code());
            case ALARM_DEACTIVATE -> alarmSystem.deactivate(event.code());
        }
    }
}
