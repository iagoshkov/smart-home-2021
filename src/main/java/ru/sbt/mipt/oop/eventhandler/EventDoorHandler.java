package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.concurrent.atomic.AtomicReference;

public class EventDoorHandler implements EventHandler {
    protected final SmartHome smartHome;

    public EventDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    protected Room findRoomByDoor(String id) {
        AtomicReference<Room> r = new AtomicReference<>();
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(doorCandidate -> {
                    if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(id)) {
                        r.set((Room) roomCandidate);
                    }
                });
            }
        });
        return r.get();
    }


    protected Door findDoorByID(String id) {
        AtomicReference<Door> door = new AtomicReference<>();
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(id)) {
                door.set((Door) doorCandidate);
            }
        });
        return door.get();
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
