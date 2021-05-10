package ru.sbt.mipt.oop.Generator.EventGenerator;
import ru.sbt.mipt.oop.Event.*;
import ru.sbt.mipt.oop.Type.EventType;

public class LightEventGenerator implements EventGenerator {

    @Override
    public SensorEvent Generate(String objectId) {
        EventType lightEventType = EventType.values()[(int) (2 * Math.random())];
        return new LightEvent(objectId, lightEventType);
    }
}
