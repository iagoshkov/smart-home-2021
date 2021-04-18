package ru.sbt.mipt.oop.event;

import com.coolcompany.smarthome.events.CCSensorEvent;

import java.util.Locale;

public class SensorEventAdapter {
    public static SensorEvent adapt(CCSensorEvent event) {
        String sensorEventTypeName = event.getEventType()
                .replace("Is", "_")
                .toUpperCase(Locale.ROOT);

        SensorEventType type = SensorEventType.NONE;

        try {
            type = SensorEventType.valueOf(sensorEventTypeName);
        } catch (IllegalArgumentException ignored) {
        }

        return new SensorEvent(type, event.getObjectId());
    }
}
