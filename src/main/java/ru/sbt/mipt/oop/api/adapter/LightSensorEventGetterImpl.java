package ru.sbt.mipt.oop.api.adapter;

import ru.sbt.mipt.oop.event_handlers.SensorEventType;

public class LightSensorEventGetterImpl implements SensorEventGetter {
    @Override
    public SensorEventType getType(String name) {
        if (name.equals("LightIsOn")) return SensorEventType.LIGHT_ON;
        if (name.equals("LightIsOff")) return SensorEventType.LIGHT_OFF;
        return null;
    }
}
