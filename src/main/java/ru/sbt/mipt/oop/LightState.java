package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightState {

    public static void handleEvent(SmartHome smartHome, SensorEvent event) {
        // событие от источника света
        for (Room room : smartHome.getRooms()) {
            room.handleLightEvent(event);
        }
    }
}
