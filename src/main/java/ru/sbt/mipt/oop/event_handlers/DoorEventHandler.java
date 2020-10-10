package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.door.Door;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_OPEN;

public class DoorEventHandler {

    public DoorEventHandler() {
    }

    public void handleDoorEvent(SensorEvent event, SmartHome smartHome, Room room) {

        for (Door door : room.getDoors()) {
            if (door.getId().equals(event.getObjectId())) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        if (room.getName().equals("hall")) {
                            ClosedHallDoorEvent closedHallDoorEvent = new ClosedHallDoorEvent();
                            closedHallDoorEvent.hallDoorClosed(smartHome);
                        } else {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        }

                    }
                }
            }
        }
    }
}

