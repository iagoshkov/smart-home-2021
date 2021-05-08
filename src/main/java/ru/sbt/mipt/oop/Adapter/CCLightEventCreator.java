package ru.sbt.mipt.oop.Adapter;
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Event.LightEvent;
import ru.sbt.mipt.oop.Type.LightEventType;

public class CCLightEventCreator implements CCEventCreator {
    private LightEventType type;
    
    public CCLightEventCreator(LightEventType type) {
        this.type = type;
    }

    @Override
    public SensorEvent create(String id) {
        return new LightEvent(id, type);
    }
}

