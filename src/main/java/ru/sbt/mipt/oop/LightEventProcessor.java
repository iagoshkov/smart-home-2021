package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.actions.LightOffAction;
import ru.sbt.mipt.oop.actions.LightOnAction;

import java.util.ArrayList;
import java.util.List;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        List<Action> actionList = new ArrayList<>();

        switch (event.getType()) {
            case LIGHT_ON -> actionList.add(new LightOnAction(event));
            case LIGHT_OFF -> actionList.add(new LightOffAction(event));
        }

        for (Action action : actionList) {
            smartHome.execute(action);
        }
    }

}
