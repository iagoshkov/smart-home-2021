package ru.sbt.mipt.oop.decorator;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.signaling.ActivatedStatus;
import ru.sbt.mipt.oop.signaling.AlarmStatus;
import ru.sbt.mipt.oop.signaling.Signaling;

public class SecurityDecorator implements EventHandler{
    private EventHandler eventHandler;
    private Signaling signaling;

    public SecurityDecorator(EventHandler eventHandler, Signaling signaling) {
        this.eventHandler = eventHandler;
        this.signaling = signaling;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (signaling.getStatus() instanceof ActivatedStatus) {
            signaling.turnOnAlarm();
        }
        if (signaling.getStatus() instanceof AlarmStatus) {
            System.out.println("Sending sms");
            return;
        }
        eventHandler.handleEvent(event);
    }
}
