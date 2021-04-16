package ru.sbt.mipt.oop.event.handler;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventAdapter;

public record SensorEventHandlerAdapter(EventHandler actual) implements com.coolcompany.smarthome.events.EventHandler {
    @Override
    public void handleEvent(CCSensorEvent foreignEvent) {
        SensorEvent event = SensorEventAdapter.adapt(foreignEvent);
        actual.handleEvent(event);
    }
}
