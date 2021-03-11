package ru.sbt.mipt.oop;

public class LightOnHandler implements EventHandler {

    @Override
    public CommandType handleEvent(Room room, Light light, Door door) {
        light.setOn(true);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        return null;
    }

}
