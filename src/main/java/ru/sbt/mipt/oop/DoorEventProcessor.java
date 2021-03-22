package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class DoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        List<Action> actionList = new ArrayList<>();

        switch (event.getType()) {
            case DOOR_OPEN:
                actionList.add(new DoorOpenAction(event));
                break;
            case DOOR_CLOSED:
                actionList.add(new DoorClosedAction(event));
                break;
            default:
                // do nothing
        }

        for (Action action : actionList) {
            smartHome.execute(action);
        }
    }

}
