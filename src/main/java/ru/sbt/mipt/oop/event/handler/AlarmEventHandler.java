package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.event.AlarmEvent;

public class AlarmEventHandler {
    public void handleEvent(Alarm alarm, AlarmEvent event) {
        switch (event.getType()) {
            case ALARM_ACTIVATE -> alarm.activate(event.getCode());
            case ALARM_DEACTIVATE -> alarm.deactivate(event.getCode());
        }
    }
}
