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
    private final HallDoorEventHandler hallDoorEventHandler;

    public AnyEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
        this.doorEventProcessor = new DoorEventProcessor(smartHome);
        this.lightEventProcessor = new LightEventProcessor(smartHome);
        this.hallDoorEventHandler = new HallDoorEventHandler(smartHome);
    }



    @Override
    public void processEvent(SensorEvent event){
        lightEventProcessor.processEvent(event);
        doorEventProcessor.processEvent(event);

    }
}