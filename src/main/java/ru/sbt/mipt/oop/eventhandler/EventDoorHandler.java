package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;


public class EventDoorHandler implements EventHandler {
    protected final SmartHome smartHome;

    public EventDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    protected Room findRoomByDoor(String id) {
        final Room[] r = {null};
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(doorCandidate -> {
                    if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(id)) {
                        r[0] = (Room) roomCandidate;
                    }
                });
            }
        });
        return r[0];
    }


    protected Door findDoorByID(String id) {
        final Door[] door = {null};
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(id)) {
                door[0] = (Door) doorCandidate;
            }
        });
        return door[0];
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
        Door door = findDoorByID(event.getObjectId());
        if (door == null) return;
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    protected void closeDoor(SensorEvent event) {
        Room room = findRoomByDoor(event.getObjectId());
        Door door = findDoorByID(event.getObjectId());
        if (door == null) return;
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
    }

    private void handleDoorClosedEvent(SensorEvent event) {
        closeDoor(event);
    }
}
