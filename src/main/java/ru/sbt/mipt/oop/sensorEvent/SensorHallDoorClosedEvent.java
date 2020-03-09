package ru.sbt.mipt.oop.sensorEvent;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.objects.*;

public class SensorHallDoorClosedEvent extends SensorDoorClosedEvent{
    public SensorHallDoorClosedEvent(String objectId) {
        super(objectId);
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private void turnOffLights(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    @Override
    public void handleEvent(SmartHome smartHome) {
        closeDoor(smartHome);
        Location<Door> location = smartHome.findDoorByID(getObjectId());
        if (location.getRoom().getName().equals("hall")) {
            turnOffLights(smartHome);
        }
    }
}
