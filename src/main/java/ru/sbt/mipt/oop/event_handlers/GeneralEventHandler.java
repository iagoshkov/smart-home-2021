package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.*;

public class GeneralEventHandler {
    private SensorEvent event;
    private SmartHome smartHome;

    public GeneralEventHandler(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public SmartHome applyEvent(){
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            LightEventHandler lightEventHandler = new LightEventHandler(event, smartHome);
            smartHome = lightEventHandler.handleLightEvent();
        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            DoorEventHandler doorEventHandler = new DoorEventHandler(event, smartHome);
            smartHome = doorEventHandler.handleDoorEvent();
        }
        return smartHome;
    }

}
