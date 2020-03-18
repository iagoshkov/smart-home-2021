package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.event.SensorAlarmEvent;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signaling.ActivationException;

public class EventSignalingHandler implements EventHandler {
    private final SmartHome smartHome;

    public EventSignalingHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event instanceof SensorAlarmEvent) {
            if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
                try {
                    smartHome.getSignaling().activateSignaling(((SensorAlarmEvent) event).getCode());
                } catch (ActivationException ignored) { }
            } else if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
                smartHome.getSignaling().deactivateSignaling(((SensorAlarmEvent) event).getCode());
            }
        }
    }
}
