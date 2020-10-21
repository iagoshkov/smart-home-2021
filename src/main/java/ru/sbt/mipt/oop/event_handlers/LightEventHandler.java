package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.actions.LightOffAction;
import ru.sbt.mipt.oop.actions.LightOnAction;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.event_handlers.SensorEventType.LIGHT_ON;

public class LightEventHandler implements GeneralEvent{

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        LightOnAction lightOnAction = new LightOnAction(light);
                        smartHome.execute(lightOnAction);
                    } else if (event.getType() == LIGHT_OFF) {
                        LightOffAction lightOffAction = new LightOffAction(light);
                        smartHome.execute(lightOffAction);
                    }
                }
            }
        }
    }
}