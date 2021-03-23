package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.DoorSensorEventHandler;
import ru.sbt.mipt.oop.handlers.GeneralSensorEventHandler;
import ru.sbt.mipt.oop.handlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.handlers.SensorEventHandler;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.*;

public class EventProcessor {
    public final SmartHome smartHome;

    public EventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
//        SensorEventHandler sensorEventHandler = new SensorEventQualifier().qualifyEvent(smartHome, event);
        SensorEventHandler sensorEventHandler = new GeneralSensorEventHandler(smartHome, event);
//        if (sensorEventHandler == null) {
//            return;
//        }
        sensorEventHandler.handleEvent();
    }
}
