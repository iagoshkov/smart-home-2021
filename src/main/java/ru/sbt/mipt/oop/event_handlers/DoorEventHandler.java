package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.door.Door;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements GeneralEvent{

    ClosedHallDoorEventHandler closedHallDoorEventHandler;

    public DoorEventHandler(ClosedHallDoorEventHandler closedHallDoorEventHandler) {
        this.closedHallDoorEventHandler = closedHallDoorEventHandler;
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                        } else if (event.getType() == DOOR_CLOSED) {
                            if (room.getName().equals("hall")) {
                                door.setOpen(false);
                                closedHallDoorEventHandler.handleEvent(event, smartHome);
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
}

