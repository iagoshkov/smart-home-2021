package ru.sbt.mipt.oop.smarthome.events;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.smarthome.events.handlers.EventHandler;

import java.util.List;

public class EventManagerCCAdapter implements EventManager {
    private SensorEventsManager ccEventsManager = new SensorEventsManager();
    private CCEventAdapter eventAdapter = new CCEventAdapter();

    public EventManagerCCAdapter(List<EventHandler> handlers) {
        for (EventHandler handler : handlers) {
            ccEventsManager.registerEventHandler(event ->
                    {handler.handle(eventAdapter.adapt(event));});
        }
    }

    @Override
    public void runCycle() {
        ccEventsManager.start();
    }
}
