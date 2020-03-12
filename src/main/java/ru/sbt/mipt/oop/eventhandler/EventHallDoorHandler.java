package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class EventHallDoorHandler implements EventHandler {
    private final SmartHome smartHome;
    private final EventDoorHandler handler;

    public EventHallDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
        this.handler = new EventDoorHandler(smartHome);
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED && handler.findRoomByDoor(event.getObjectId()) != null && handler.findRoomByDoor(event.getObjectId()).getName().equals("hall")) {
            handleHallDoorClosedEvent(event);
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
        Room room = handler.findRoomByDoor(sensorEvent.getObjectId());
        Door door = handler.findDoorByID(sensorEvent.getObjectId());
        if (door == null) return;
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        turnOffLights(smartHome);
    }
}
