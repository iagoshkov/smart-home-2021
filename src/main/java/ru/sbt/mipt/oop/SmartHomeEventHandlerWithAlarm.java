package ru.sbt.mipt.oop;

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
