package ru.sbt.mipt.oop.event_handlers;
import ru.sbt.mipt.oop.actions.DoorClosedAction;
import ru.sbt.mipt.oop.actions.DoorOpenAction;
import ru.sbt.mipt.oop.actions.LightOffAction;
import ru.sbt.mipt.oop.actions.LightOnAction;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.ArrayList;

public class EventSolverImplementation implements EventSolver{


    public EventSolverImplementation() {}

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
            if (event.getType() == SensorEventType.DOOR_CLOSED) {
                DoorClosedAction doorClosedAction = new DoorClosedAction(event.getObjectId());
                smartHome.execute(doorClosedAction);
            }
            if (event.getType() == SensorEventType.DOOR_OPEN) {
                DoorOpenAction doorOpenAction = new DoorOpenAction(event.getObjectId());
                smartHome.execute(doorOpenAction);
            }
            if (event.getType() == SensorEventType.LIGHT_OFF) {
                LightOffAction lightOffAction = new LightOffAction(event.getObjectId());
                smartHome.execute(lightOffAction);
            }
            if (event.getType() == SensorEventType.LIGHT_ON) {
                LightOnAction lightOnAction = new LightOnAction(event.getObjectId());
                smartHome.execute(lightOnAction);
            }
    }
}
