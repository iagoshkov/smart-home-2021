package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.smart.actions.*;

public enum SensorEventType {
    LIGHT_ON(new ActionLightOn()),
    LIGHT_OFF(new ActionLightOff()),
    DOOR_OPEN(new ActionDoorOpen()),
    DOOR_CLOSED(new ActionDoorClose());

    private final Actionable action;

    SensorEventType(Actionable action) {
        this.action = action;
    }

    public Actionable getAction() {
        return action;
    }
}
