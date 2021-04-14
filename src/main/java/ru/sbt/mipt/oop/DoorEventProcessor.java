package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.actions.DoorClosedAction;
import ru.sbt.mipt.oop.actions.DoorOpenAction;

import java.util.ArrayList;
import java.util.List;

public class DoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        List<Action> actionList = new ArrayList<>();

        switch (event.getType()) {
            case DOOR_OPEN -> actionList.add(new DoorOpenAction(event));
            case DOOR_CLOSED -> actionList.add(new DoorClosedAction(event));
        }

        for (Action action : actionList) {
            smartHome.execute(action);
        }
    }

}
