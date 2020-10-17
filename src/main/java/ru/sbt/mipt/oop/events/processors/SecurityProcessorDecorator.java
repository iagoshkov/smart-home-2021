package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.Alarm;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public class SecurityProcessorDecorator implements EventProcessor {
    private final EventProcessor source;

    public SecurityProcessorDecorator(EventProcessor source) throws IllegalArgumentException {
        if (source == null) throw new IllegalArgumentException();
        this.source = source;
    }

    @Override
    public void processing(SensorEvent event, SmartHome smartHome) {
        Alarm alarm = smartHome.getAlarm();

        if (alarm == null || isAlarm(event)) {
            source.processing(event, smartHome);
            return;
        }

        if (alarm.isActivated()) alarm.activateAlert();
        if (alarm.isAlert()) {
            System.out.println("Sending sms");
            return;
        }

        source.processing(event, smartHome);
    }

    private boolean isAlarm(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_DEACTIVATE || event.getType() == SensorEventType.ALARM_ACTIVATE;
    }
}
