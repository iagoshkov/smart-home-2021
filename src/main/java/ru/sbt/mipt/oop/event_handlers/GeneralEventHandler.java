package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public class GeneralEventHandler {
    private final SensorEvent event;
    private SmartHome smartHome;

    public GeneralEventHandler(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public SmartHome applyEvent(){
        EventClassifier eventClassifier = new EventClassifier();
        smartHome = eventClassifier.returnSmartHomeAfterClassifiedEvent(smartHome, event);
        return smartHome;
    }

}
