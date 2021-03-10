package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightSmartHomeHandler extends SmartHomeHandlerImpl{
    public LightSmartHomeHandler(SmartHome smartHome, OutputStream output) {
        super(smartHome, output);
    }

    @Override
    public void handleEvent(SensorEvent event) {
        // событие от источника света
        Light light = findLight(event);
        if (light != null) {
            switchLight(currentRoom, light, event.getType() == LIGHT_ON);
        }
    }

    private Room currentRoom = null;

    private Light findLight(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    currentRoom = room;
                    return light;
                }
            }
        }

        return null;
    }

    private void switchLight(Room room, Light light, boolean isOn) {
        light.setOn(isOn);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was " +
                (isOn? "turned on.": "turned off."));
    }
}
