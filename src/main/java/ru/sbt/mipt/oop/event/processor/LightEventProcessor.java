package ru.sbt.mipt.oop.event.processor;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.actions.LightOffAction;
import ru.sbt.mipt.oop.actions.LightOnAction;

import java.util.ArrayList;
import java.util.List;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        List<Action> actionList = new ArrayList<>();

        switch (event.getType()) {
            case LIGHT_ON -> actionList.add(new LightOnAction(event.getObjectId()));
            case LIGHT_OFF -> actionList.add(new LightOffAction(event.getObjectId()));
        }

        for (Action action : actionList) {
            smartHome.execute(action);
        }
    }

}
