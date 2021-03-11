package ru.sbt.mipt.oop;

public class LightOffHandler implements EventHandler {

    @Override
    public CommandType handleEvent(SensorEvent event, Room room, Light light, Door door) {
        if (event.getType() != SensorEventType.LIGHT_OFF ||
                !light.getId().equals(event.getObjectId())) return null;

        light.setOn(false);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        return null;
    }

}
