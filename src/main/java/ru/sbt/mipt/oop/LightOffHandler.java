package ru.sbt.mipt.oop;

public class LightOffHandler implements EventHandler {

    @Override
    public CommandType handleEvent(Room room, Light light, Door door) {
        light.setOn(false);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        return null;
    }

}
