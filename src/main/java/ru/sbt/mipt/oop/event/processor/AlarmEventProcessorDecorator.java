package ru.sbt.mipt.oop.event.processor;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.event.AlarmEvent;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.handler.AlarmEventHandler;

public record AlarmEventProcessorDecorator(EventProcessor wrapped,
                                           Alarm alarm,
                                           AlarmEventHandler alarmEventHandler) implements EventProcessor {
    @Override
    public void processEvent(Event event) {
        if (event instanceof AlarmEvent alarmEvent) {
            System.out.println("Got event: " + event);

            alarmEventHandler.handleEvent(alarm, alarmEvent);
        } else if (wrapped != null && alarm.allowSensorEvents()) {
            wrapped.processEvent(event);
        }
    }
}
