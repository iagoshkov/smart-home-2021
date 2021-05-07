package ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.Type.LightEventType;

public class LightEvent extends SensorEvent{
    private LightEventType lightEventType;

    public LightEvent(String objectId, LightEventType lightEventType) {
        super(objectId);
        this.lightEventType = lightEventType;
    }

    public LightEventType getLightEventType() {
        return lightEventType;
    }

    @Override
    public String toString() {
        return "LightEvent{" + "lightEventType=" + lightEventType + ", objectId=" + super.getObjectId() + "}";
    }
}
