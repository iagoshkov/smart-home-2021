package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class ReceiverSmartHomeEventsImpl implements ReceiverSmartHomeEvents {
    @Override
    public void handleEvents(EventsSource eventsSource, SmartHome executor, OutputStream output) {
        SensorEvent event = eventsSource.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            SmartHomeHandler handler = null;

            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                handler = new LightSmartHomeHandler(executor, output);
            } else if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                handler = new DoorSmartHomeHandler(executor, output);
            }

            if (handler != null) {
                handler.handleEvent(event);
            }

            event = eventsSource.getNextSensorEvent();
        }
    }
}
