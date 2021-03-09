package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.DoorSensorEventHandler;
import ru.sbt.mipt.oop.handlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.handlers.SensorEventHandler;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventProcessor {
    public final SmartHome smartHome;

    public EventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        SensorEventHandler sensorEventHandler;
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            sensorEventHandler = new LightSensorEventHandler(smartHome.getRooms(), event);
        } else if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            sensorEventHandler = new DoorSensorEventHandler(smartHome.getRooms(), event);
        } else {
            return;
        }
        sensorEventHandler.handleEvent();
    }
}
