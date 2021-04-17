package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.actions.TurnLightOffAction;
import ru.sbt.mipt.oop.actions.TurnLightOnAction;

import java.util.ArrayList;
import java.util.List;

public class LightEventHandler implements EventHandler {

    private final SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        List<Action> actionList = new ArrayList<>();

        switch (event.getType()) {
            case LIGHT_ON -> actionList.add(new TurnLightOnAction(event.getObjectId()));
            case LIGHT_OFF -> actionList.add(new TurnLightOffAction(event.getObjectId()));
        }

        for (Action action : actionList) {
            smartHome.execute(action);
        }
    }

}
