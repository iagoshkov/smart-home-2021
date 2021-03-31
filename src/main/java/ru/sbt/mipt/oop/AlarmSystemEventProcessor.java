package ru.sbt.mipt.oop;

public class AlarmSystemEventProcessor {
    public void processEvent(AlarmSystem alarmSystem, AlarmSystemEvent event) {
        switch (event.type()) {
            case ALARM_ACTIVATE -> alarmSystem.activate(event.code());
            case ALARM_DEACTIVATE -> alarmSystem.deactivate(event.code());
        }
    }
}