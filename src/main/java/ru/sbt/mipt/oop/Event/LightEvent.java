package ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.Type.EventType;

public class LightEvent extends SensorEvent{
    private EventType lightEventType;

    public LightEvent(String objectId, EventType lightEventType) {
        super(objectId);
        this.lightEventType = lightEventType;
    }

    public EventType getLightEventType() {
        return lightEventType;
    }

    @Override
    public String toString() {
        return "LightEvent{" + "lightEventType=" + lightEventType + ", objectId=" + super.getObjectId() + "}";
    }
}
