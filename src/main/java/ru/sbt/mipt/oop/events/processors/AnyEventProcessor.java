package ru.sbt.mipt.oop.events.processors;


import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class AnyEventProcessor implements EventProcessor {
    private final SmartHome smartHome;
    private final DoorEventProcessor doorEventProcessor;
    private final LightEventProcessor lightEventProcessor;

    public AnyEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
        this.doorEventProcessor = new DoorEventProcessor(smartHome);
        this.lightEventProcessor = new LightEventProcessor(smartHome);
    }

    private boolean isLightEvent(SensorEvent event) {
        return (event.getType().equals(SensorEventType.LIGHT_ON) || event.getType().equals(SensorEventType.LIGHT_OFF));
    }

    private boolean isDoorEvent(SensorEvent event) {
        return (event.getType().equals(DOOR_OPEN) || event.getType().equals(DOOR_CLOSED));
    }

    @Override
    public void processEvent(SensorEvent event){
        if (isLightEvent(event)) {
            // событие от источника света
            lightEventProcessor.processEvent(event);
        }

        if (isDoorEvent(event)) {
            // событие от двери
            doorEventProcessor.processEvent(event);
        }
    }
}