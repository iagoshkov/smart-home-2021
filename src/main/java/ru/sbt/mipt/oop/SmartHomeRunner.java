package ru.sbt.mipt.oop;

public class SmartHomeRunner implements HomeRunner {

    private final HomeInteraction homeInteraction;
    private final EventHandler eventHandler;

    public SmartHomeRunner(HomeInteraction homeInteraction, EventHandler eventHandler) {
        this.homeInteraction = homeInteraction;
        this.eventHandler = eventHandler;
    }

    @Override
    public void runHome() {
        SensorEvent event = homeInteraction.getNextSensorEvent();

        while (event != null) {
            System.out.println("Got event: " + event);
            eventHandler.handleEvent(event);
            event = homeInteraction.getNextSensorEvent();
        }
    }

}
