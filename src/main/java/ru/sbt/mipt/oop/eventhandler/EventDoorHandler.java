package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class EventDoorHandler implements EventHandler {
    private final SmartHome smartHome;

    public EventDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_OPEN) {
            handleDoorOpenEvent(event);
        } else if (event.getType() == SensorEventType.DOOR_CLOSED) {
            handleDoorClosedEvent(event);
        }
    }

    private void handleDoorOpenEvent(SensorEvent event) {
        Room room = smartHome.findRoomByDoor(event.getObjectId());
        Door door = smartHome.findDoorByID(room, event.getObjectId());
        if (door == null) return;
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    private void closeDoor(SensorEvent sensorEvent) {
        Room room = smartHome.findRoomByDoor(sensorEvent.getObjectId());
        Door door = smartHome.findDoorByID(room, sensorEvent.getObjectId());
        if (door == null) return;
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
    }

    private void handleDoorClosedEvent(SensorEvent event) {
        Room room = smartHome.findRoomByDoor(event.getObjectId());
        Door door = smartHome.findDoorByID(room, event.getObjectId());
        if (door == null) return;
        if (room.getName().equals("hall")) {
            handleHallDoorClosedEvent(event);
        } else {
            closeDoor(event);
        }
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

    private void handleHallDoorClosedEvent(SensorEvent sensorEvent) {
        closeDoor(sensorEvent);
        turnOffLights(smartHome);
    }
}
