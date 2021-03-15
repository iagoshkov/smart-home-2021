package ru.sbt.mipt.oop;

public class LightOnHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (!light.getId().equals(event.getObjectId())) continue;

                light.execute(new LightOnAction());
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            }
        }
    }

}
