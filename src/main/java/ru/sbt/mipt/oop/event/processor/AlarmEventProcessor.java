package ru.sbt.mipt.oop.event.processor;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.event.AlarmEvent;

public class AlarmEventProcessor {
    public void processEvent(Alarm alarm, AlarmEvent event) {
        switch (event.type()) {
            case ALARM_ACTIVATE -> alarm.activate(event.code());
            case ALARM_DEACTIVATE -> alarm.deactivate(event.code());
        }
    }
}
