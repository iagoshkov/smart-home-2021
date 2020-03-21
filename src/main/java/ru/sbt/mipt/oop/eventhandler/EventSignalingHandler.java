package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.event.SensorAlarmEvent;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signaling.Signaling;

public class EventSignalingHandler implements EventHandler {
    private final SmartHome smartHome;

    public EventSignalingHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (smartHome.getSignaling(new Signaling()) == null) {
            return;
        }
        if (event instanceof SensorAlarmEvent) {
            if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
                smartHome.getSignaling(new Signaling()).activateSignaling(((SensorAlarmEvent) event).getCode());
            } else if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
                smartHome.getSignaling(new Signaling()).deactivateSignaling(((SensorAlarmEvent) event).getCode());
            }
        }
    }
}
