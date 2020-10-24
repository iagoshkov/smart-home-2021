package ru.sbt.mipt.oop.events.adpter;

import ru.sbt.mipt.oop.events.SensorEventType;

public class LightSensorEventFactory implements SensorEventFactory {
    private static LightSensorEventFactory singleton;

    public static LightSensorEventFactory getFactory() {
        if (singleton == null) singleton = new LightSensorEventFactory();
        return singleton;
    }

    @Override
    public SensorEventType getType(String typeName) {
        switch (typeName) {
            case "LightIsOn":
                return SensorEventType.LIGHT_ON;
            case "LightIsOff":
                return SensorEventType.LIGHT_OFF;
            default:
                return null;
        }
    }
}
