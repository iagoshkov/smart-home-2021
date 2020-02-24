package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {
    private final HomeConditionPersister homeConditionPersister;

    public Application(HomeConditionGsonPersister homeConditionGsonPersister) {
        this.homeConditionPersister = homeConditionGsonPersister;
    }

    public static void main(String... args) throws IOException {
        new Application(new HomeConditionGsonPersister()).handleEvents();
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

    private void handleEvents() throws IOException {
        EventHandler handler = new EventHandler(homeConditionPersister.readHome());
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            handler.handleEvent(event);
            event = getNextSensorEvent();
        }
    }
}
