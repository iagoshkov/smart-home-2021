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
    protected final SmartHome smartHome;

    public EventDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    protected Room findRoomByDoor(String id) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    return room;
                }
            }
        }
        return null;
    }

    protected Door findDoorByID(Room room, String id) {
        if (room == null) return null;
        for (Door door : room.getDoors()) {
            if (door.getId().equals(id)) {
                return door;
            }
        }
        return null;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_OPEN) {
            handleDoorOpenEvent(event);
        } else if (event.getType() == SensorEventType.DOOR_CLOSED && findRoomByDoor(event.getObjectId()) != null && !findRoomByDoor(event.getObjectId()).getName().equals("hall")) {
            handleDoorClosedEvent(event);
        }
    }

    private void handleDoorOpenEvent(SensorEvent event) {
        Room room = findRoomByDoor(event.getObjectId());
        Door door = findDoorByID(room, event.getObjectId());
        if (door == null) return;
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    protected void closeDoor(SensorEvent event) {
        Room room = findRoomByDoor(event.getObjectId());
        Door door = findDoorByID(room, event.getObjectId());
        if (door == null) return;
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
    }

    private void handleDoorClosedEvent(SensorEvent event) {
        closeDoor(event);
    }
}
