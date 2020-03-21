package ru.sbt.mipt.oop.decorator;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.signaling.ActivatedStatus;
import ru.sbt.mipt.oop.signaling.AlarmStatus;
import ru.sbt.mipt.oop.signaling.Signaling;

import java.util.Collection;

public class SecurityDecorator implements EventHandler{
    private Collection<EventHandler> eventHandlers;
    private Signaling signaling;

    public SecurityDecorator(Collection<EventHandler> eventHandlers, Signaling signaling) {
        this.eventHandlers = eventHandlers;
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
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handleEvent(event);
        }
    }
}
