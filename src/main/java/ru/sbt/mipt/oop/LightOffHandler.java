package ru.sbt.mipt.oop;

public class LightOffHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (!light.getId().equals(event.getObjectId())) continue;

                light.execute(new LightOffAction());
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }

}
