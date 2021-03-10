package ru.sbt.mipt.oop;

public class SmartHomeRunner implements HomeRunner {

    private final HomeSupervision homeSupervision;
    private final EventHandler eventHandler;

    public SmartHomeRunner(HomeSupervision homeSupervision, EventHandler eventHandler) {
        this.homeSupervision = homeSupervision;
        this.eventHandler = eventHandler;
    }

    @Override
    public void runHome() {
        SensorEvent event = homeSupervision.getNextSensorEvent();

        while (event != null) {
            System.out.println("Got event: " + event);
            eventHandler.handleEvent(event);
            event = homeSupervision.getNextSensorEvent();
        }
    }

}
