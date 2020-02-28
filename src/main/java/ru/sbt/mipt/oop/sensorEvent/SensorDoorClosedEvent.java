package ru.sbt.mipt.oop.sensorEvent;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.objects.*;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;


public class SensorDoorClosedEvent extends SensorEvent {
    public SensorDoorClosedEvent(String objectId) {
        super(objectId);
        this.type = DOOR_CLOSED;
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
        Location<Door> location = smartHome.findDoorByID(getObjectId());
        if (location == null) return;;
        location.getObject().setOpen(false);
        System.out.println("Door " + location.getObject().getId() + " in room " + location.getRoom().getName() + " was closed.");

        if (location.getRoom().getName().equals("hall")) {
            turnOffLights(smartHome);
        }
    }
}
