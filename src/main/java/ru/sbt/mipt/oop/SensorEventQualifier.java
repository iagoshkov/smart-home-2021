package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.DoorSensorEventHandler;
import ru.sbt.mipt.oop.handlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.handlers.SensorEventHandler;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.*;
import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;

public class SensorEventQualifier {
    public SensorEventHandler qualifyEvent (SmartHome smartHome, SensorEvent event) {
        SensorEventHandler sensorEventHandler;
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            sensorEventHandler = new LightSensorEventHandler(smartHome, event);
        } else if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            sensorEventHandler = new DoorSensorEventHandler(smartHome, event);
        } else {
            return null;
        }
        return sensorEventHandler;
    }
}
