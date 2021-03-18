package ru.sbt.mipt.oop;

public class LightOffHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != SensorEventType.LIGHT_OFF) return;

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (!light.getId().equals(event.getObjectId())) continue;

                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }

}
