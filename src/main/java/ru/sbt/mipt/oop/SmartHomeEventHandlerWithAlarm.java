package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmEvent;
import ru.sbt.mipt.oop.alarm.AlarmEventProcessor;

public record SmartHomeEventHandlerWithAlarm(SmartHomeEventHandler wrappedEventHandler,
                                             AlarmEventProcessor eventProcessor,
                                             Alarm alarmSystem) implements SmartHomeEventHandler {
    @Override
    public void handleEvent(Event event) {
        if (event instanceof AlarmEvent alarmEvent) {
            System.out.println("Got event: " + event);

            eventProcessor.processEvent(alarmSystem, alarmEvent);
        } else if (wrappedEventHandler != null && alarmSystem.allowSensorEvents()) {
            wrappedEventHandler.handleEvent(event);
        }
    }
}
