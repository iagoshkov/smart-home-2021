package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.alarm.ActivateState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.WarningState;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.ArrayList;

public class EventSolverDecorator implements EventSolver {

    private Alarm alarm;
    ArrayList<GeneralEvent> events = new ArrayList<>();;
    LightEventHandler lightEventHandler = new LightEventHandler();
    DoorEventHandler doorEventHandler = new DoorEventHandler();

    EventSolverDecorator(SmartHome smartHome) {
        this.alarm = smartHome.getAlarm();
        events.add(lightEventHandler);
        events.add(doorEventHandler);
    }

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {

        if (alarm.getState() instanceof ActivateState) {
            alarm.alarm();
        }

        if (alarm.getState() instanceof WarningState) {
            System.out.println("SMS: ALARM!!");
            return;
        }

        for (GeneralEvent event1 : events) {
            event1.handleEvent(event, smartHome);
        }
    }
}
