package ru.sbt.mipt.oop.event_handlers;
import ru.sbt.mipt.oop.actions.*;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.ArrayList;

public class EventSolverImplementation implements EventSolver{


    public EventSolverImplementation() {}

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED | event.getType() == SensorEventType.DOOR_OPEN){
            DoorAction doorAction = new DoorAction(event, smartHome);
            smartHome.execute(doorAction);
        }
        if (event.getType() == SensorEventType.LIGHT_OFF | event.getType() == SensorEventType.LIGHT_ON) {
            LightAction lightAction = new LightAction(event);
            smartHome.execute(lightAction);
        }
    }
}
