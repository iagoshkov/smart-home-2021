package ru.sbt.mipt.oop;

public record SmartHomeEventHandlerWithAlarmSystem(SmartHomeEventHandler wrappedEventHandler,
                                                   AlarmSystemEventProcessor eventProcessor,
                                                   AlarmSystem alarmSystem) implements SmartHomeEventHandler {
    @Override
    public void handleEvent(Event event) {
        if (event instanceof AlarmSystemEvent alarmSystemEvent) {
            System.out.println("Got event: " + event);

            eventProcessor.processEvent(alarmSystem, alarmSystemEvent);
        } else if (wrappedEventHandler != null && alarmSystem.getState().allowSensorEvents()) {
            wrappedEventHandler.handleEvent(event);
        }
    }
}
