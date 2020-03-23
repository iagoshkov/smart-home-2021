package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.eventhandler.EventHandler;

public class SensorEventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final EventHandler eventHandler;

    public SensorEventHandlerAdapter(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEventType type = ConverterTypes.convertType.get(event.getEventType());
        if (type == null) return;
        eventHandler.handleEvent(new SensorEvent(type, event.getObjectId()));
    }
}
