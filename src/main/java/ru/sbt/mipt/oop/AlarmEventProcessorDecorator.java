package ru.sbt.mipt.oop;

import java.util.List;

public class AlarmEventProcessorDecorator implements EventProcessor {
    private List<EventProcessor> processors;

    public AlarmEventProcessorDecorator(List<EventProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (smartHome.getAlarm().isActivated() && event.getType() != SensorEventType.ALARM_DEACTIVATE) {
            smartHome.getAlarm().alert();
            System.out.println("Sending sms");
        } else {
            if (smartHome.getAlarm().isDeactivated()) {
                for (EventProcessor processor : processors) {
                    processor.processEvent(smartHome, event);
                }
            }
        }
    }
}

