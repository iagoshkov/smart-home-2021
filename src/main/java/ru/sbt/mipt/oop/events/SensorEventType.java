package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.smart.actions.*;

public enum SensorEventType {
    LIGHT_ON(new ActionLightOn()),
    LIGHT_OFF(new ActionLightOff()),
    DOOR_OPEN(new ActionDoorOpen()),
    DOOR_CLOSED(new ActionDoorClose()),
    TURNOFF_ALL_LIGHTS_IN_SMART_HOME(new ActionTurnOffAllLightsInSmartHome());

    private final Action[] actions;

    SensorEventType(Action... actions) {
        this.actions = actions;
    }

    public Action[] getActions() {
        return actions;
    }
}