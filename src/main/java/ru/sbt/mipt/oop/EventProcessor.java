package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.ArrayList;
import java.util.Arrays;

import static ru.sbt.mipt.oop.sensors.SensorEventType.*;

public class EventProcessor {
    public final SmartHome smartHome;

    public EventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent(SensorEvent event) {
        System.out.println("Got event: " + event);

        SensorEventHandler sensorEventHandler = new GeneralSensorEventHandler(
                smartHome,
                event,
                Arrays.asList(
                        new LightSensorEventHandler(smartHome, event),
                        new DoorSensorEventHandler(smartHome, event),
                        new HallDoorSensorEventHandler(smartHome, event)
                ));
        sensorEventHandler.handleEvent();
    }
}
