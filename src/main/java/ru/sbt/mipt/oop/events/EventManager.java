package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.smart.actions.Action;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public class EventManager {
    public void executeEvent(SensorEventType eventType, SmartDevice smartDevice) {
        if (eventType == null) return;
        for(Action action: eventType.getActions()) {
            smartDevice.execute(action);
        }
    }

    public SensorEvent getNextSensorEvent() {
        if (Math.random() < 0.05D) return null;

        SensorEventType sensorEventType = SensorEventType.values()[(int)(4.0D * Math.random())];
        int offset = sensorEventType != SensorEventType.LIGHT_ON && sensorEventType != SensorEventType.LIGHT_OFF ? 6 : 1;
        String objectId = "" + ((int)(4.0D * Math.random()) + offset);
        return new SensorEvent(sensorEventType, objectId);
        }
    }
