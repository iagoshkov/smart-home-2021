package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.CloseDoorAction;
import ru.sbt.mipt.oop.actions.OpenDoorAction;
import ru.sbt.mipt.oop.event.SensorEvent;

import java.util.ArrayList;
import java.util.List;

public class DoorEventHandler implements EventHandler {

    private final SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        List<Action> actionList = new ArrayList<>();

        switch (event.getType()) {
            case DOOR_OPEN -> actionList.add(new OpenDoorAction(event.getObjectId()));
            case DOOR_CLOSED -> actionList.add(new CloseDoorAction(event.getObjectId()));
        }

        for (Action action : actionList) {
            smartHome.execute(action);
        }
    }

}
