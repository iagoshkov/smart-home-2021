package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) {
        JsonSmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        SmartHome smartHome = smartHomeReader.read();

        // SRP & IOP & LSP
        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(smartHome),
                new DoorEventProcessor(smartHome));

        // SRP
        SmartHomeEventHandler smartHomeEventHandler = new SmartHomeEventHandler(smartHome, eventProcessors);

        for (SensorEvent event = getNextSensorEvent(); event != null; event = getNextSensorEvent()) {
            smartHomeEventHandler.handleEvent(event);
        }
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
