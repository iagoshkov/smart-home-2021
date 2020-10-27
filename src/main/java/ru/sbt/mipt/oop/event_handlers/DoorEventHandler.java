package ru.sbt.mipt.oop.event_handlers;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.door.Door;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.*;

@Component
public class DoorEventHandler implements GeneralEvent{

    public DoorEventHandler() {
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
                                ClosedHallDoorEvent closedHallDoorEvent = new ClosedHallDoorEvent();
                                closedHallDoorEvent.hallDoorClosed(smartHome);
                            } else {
                                door.setOpen(false);
                                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                            }
                        }
                        if (event.getType() == DOOR_LOCKED) {
                            door.setLocked(true);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was locked.");
                        }
                        if (event.getType() == DOOR_UNLOCKED) {
                            door.setLocked(false);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was unlocked.");
                        }
                    }
                }
            }
        }
    }
}

