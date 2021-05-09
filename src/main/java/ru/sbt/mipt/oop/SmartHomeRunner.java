package ru.sbt.mipt.oop;

public class SmartHomeRunner {
    private final EventManager eventManager;
    private final SmartHomeEventSource eventSource;

    public SmartHomeRunner(EventManager eventManager, SmartHomeEventSource eventSource) {
        this.eventManager = eventManager;
        this.eventSource = eventSource;
    }

    public void runLoop() {
        SensorEvent event = eventSource.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventManager.manageEvent(event);
            event = eventSource.getNextSensorEvent();
        }
    }
}
