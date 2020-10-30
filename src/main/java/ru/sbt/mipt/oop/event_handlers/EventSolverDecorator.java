package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.alarm.ActivateStateImplementation;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.WarningState;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.List;

public class EventSolverDecorator implements EventSolver {

    private final Alarm alarm;

    private final List<GeneralEvent> eventHandlersList;


    public EventSolverDecorator(List<GeneralEvent> eventHandlersList, SmartHome smartHome) {
        this.alarm = smartHome.getAlarm();
        this.eventHandlersList = eventHandlersList;
    }

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
        if (alarm.getState() instanceof ActivateStateImplementation) {
            alarm.alarm();
        }
        if (alarm.getState() instanceof WarningState) {
            System.out.println("SMS: ALARM!!");
            return;
        }
        for (GeneralEvent event1 : eventHandlersList) {
            event1.handleEvent(event, smartHome);
        }
    }
}
