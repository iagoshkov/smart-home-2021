package ru.sbt.mipt.oop;

public class LightOnHandler implements EventHandler {

    @Override
    public CommandType handleEvent(SensorEvent event, Room room, Light light, Door door) {
        if (event.getType() != SensorEventType.LIGHT_ON ||
                !light.getId().equals(event.getObjectId())) return null;

        light.setOn(true);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        return null;
    }

}
