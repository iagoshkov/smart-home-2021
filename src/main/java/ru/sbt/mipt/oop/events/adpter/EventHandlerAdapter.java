package ru.sbt.mipt.oop.events.adpter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

public class EventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final EventHandler eventHandler;

    public EventHandlerAdapter(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEventType ownEventType = adapts(event.getEventType());
        if (ownEventType == null) return;
        SensorEvent ownEvent = new SensorEvent(ownEventType, event.getObjectId());
        eventHandler.executeEvent(ownEvent);
    }

    private SensorEventType adapts(String eventType) {
        var result = DoorSensorEventFactory.getFactory().getType(eventType);
        if (result != null) return result;

        result = LightSensorEventFactory.getFactory().getType(eventType);
        return result;
    }
}
