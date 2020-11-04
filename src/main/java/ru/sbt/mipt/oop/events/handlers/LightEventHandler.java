package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.LightOffAction;
import ru.sbt.mipt.oop.actions.LightOnAction;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.sensor.event.SensorEventType.LIGHT_ON;

public class LightEventHandler implements IEventHandler {

    public LightEventHandler() {

    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        Action action = new LightOnAction(event.getObjectId());
                        room.execute(action);
                    }
                    if (event.getType() == LIGHT_OFF) {
                        Action action = new LightOffAction(event.getObjectId());
                        room.execute(action);
                    }
                }
            }
        }
    }
}
