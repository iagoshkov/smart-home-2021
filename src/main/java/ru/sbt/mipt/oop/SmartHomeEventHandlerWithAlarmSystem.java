package ru.sbt.mipt.oop;

public class SmartHomeEventHandlerWithAlarmSystem implements SmartHomeEventHandler {

    SmartHomeEventHandler wrappedEventHandler;
    AlarmSystemEventProcessor eventProcessor;

    private final AlarmSystem alarmSystem;

    public SmartHomeEventHandlerWithAlarmSystem(SmartHomeEventHandler wrappedEventHandler,
                                                AlarmSystemEventProcessor eventProcessor,
                                                String code) {
        this.wrappedEventHandler = wrappedEventHandler;
        this.eventProcessor = eventProcessor;
        this.alarmSystem = new AlarmSystem(code);
    }

    @Override
    public void handleEvent(Event event) {
        System.out.println("Got event: " + event);

        if (event instanceof AlarmSystemEvent alarmSystemEvent) {
            eventProcessor.processEvent(alarmSystem, alarmSystemEvent);
        } else if (wrappedEventHandler != null && alarmSystem.getState().allowSensorEvents()) {
            wrappedEventHandler.handleEvent(event);
        }
    }

}
