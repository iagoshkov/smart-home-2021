package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmEvent;
import ru.sbt.mipt.oop.alarm.AlarmEventProcessor;

public record EventHandlerWithAlarm(EventHandler wrapped,
                                    AlarmEventProcessor eventProcessor,
                                    Alarm alarm) implements EventHandler {
    @Override
    public void handleEvent(Event event) {
        if (event instanceof AlarmEvent alarmEvent) {
            System.out.println("Got event: " + event);

            eventProcessor.processEvent(alarm, alarmEvent);
        } else if (wrapped != null && alarm.allowSensorEvents()) {
            wrapped.handleEvent(event);
        }
    }
}
