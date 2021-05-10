package ru.sbt.mipt.oop.Adapter;
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Event.LightEvent;
import ru.sbt.mipt.oop.Type.EventType;

public class CCLightEventCreator implements CCEventCreator {
    private EventType type;
    
    public CCLightEventCreator(EventType type) {
        this.type = type;
    }

    @Override
    public SensorEvent create(String id) {
        return new LightEvent(id, type);
    }
}

