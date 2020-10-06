package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.typedefs.DoorEventType;
import ru.sbt.mipt.oop.events.typedefs.EventType;
import ru.sbt.mipt.oop.events.typedefs.LightEventType;

import java.util.Random;

import static ru.sbt.mipt.oop.events.processors.EventProcessorType.DOOR;

public class SensorEventGenerator implements EventGenerator {
    public Random random = new Random();

    public Event getNextEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        EventType sensorEventType;
        if (Math.random() >= 0.5) {
            sensorEventType = DoorEventType.values()[random.nextInt(DoorEventType.values().length)];
        } else {
            sensorEventType = LightEventType.values()[random.nextInt(LightEventType.values().length)];
        }
        String objectId = String.valueOf(random.nextInt(10));
        if (sensorEventType.getProcessorType() == DOOR) {
            return new DoorEvent(sensorEventType, new StringId(objectId));
        } else {
            return new LightEvent(sensorEventType, new StringId(objectId));
        }
    }
}
