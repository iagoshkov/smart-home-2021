package ru.sbt.mipt.oop.event.processor;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.DoorClosedAction;
import ru.sbt.mipt.oop.actions.DoorOpenAction;

import java.util.ArrayList;
import java.util.List;

public class DoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        List<Action> actionList = new ArrayList<>();

        switch (event.getType()) {
            case DOOR_OPEN -> actionList.add(new DoorOpenAction(event.getObjectId()));
            case DOOR_CLOSED -> actionList.add(new DoorClosedAction(event.getObjectId()));
        }

        for (Action action : actionList) {
            smartHome.execute(action);
        }
    }

}
