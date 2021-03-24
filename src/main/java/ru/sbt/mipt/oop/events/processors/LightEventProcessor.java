package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.utils.Searher;

public class LightEventProcessor implements EventProcessor{
    private final SmartHome smartHome;

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private void turnOffAll() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                updateLightState(light, false);
            }
        }
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (event.getObjectId().equals("all")) {
            turnOffAll();
            return;
        }

        Light targetLight = (new Searher(smartHome)).findLight(event.getObjectId());
        if (targetLight != null) {
            updateLightState(targetLight, getLightState(event));
        }
    }

    private void updateLightState(Light light, boolean newState) {
        light.setOn(newState);
        System.out.println("Light " + light.getId() + " was turned " + (newState ? "on." : "off."));
    }

    private boolean getLightState(SensorEvent event){
        return event.getType().equals(SensorEventType.LIGHT_ON);
    }
}
