package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class LightOnHandler implements EventHandler {

    @Override
    public List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != SensorEventType.LIGHT_ON) return null;

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (!light.getId().equals(event.getObjectId())) continue;

                light.setOn(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            }
        }
        return new ArrayList<>();
    }

}
