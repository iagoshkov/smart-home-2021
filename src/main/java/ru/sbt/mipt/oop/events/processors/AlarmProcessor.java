package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.AlarmEventType;

public class AlarmProcessor implements EventProcessor {
    @Override
    public Event processEvent(SmartHome smartHome, Event event) {
        if (event.getType() != AlarmEventType.ALARM_WARNING) {
            Event newEvent = smartHome.apply(event, ((HomeComponent system) -> {
                if (event.getType() == AlarmEventType.ALARM_ACTIVATE) {
                    ((AlarmSystem) system).activate(((AlarmEvent) event).getActivationCode());
                } else {
                    ((AlarmSystem) system).deactivate(((AlarmEvent) event).getActivationCode());
                }
            }));
            return newEvent;
        }
        return event;
    }
}
