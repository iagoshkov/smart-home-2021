package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.door.Door;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_OPEN;

public class DoorEventHandler {
    private final SensorEvent event;
    private final SmartHome smartHome;

    public DoorEventHandler(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public SmartHome handleDoorEvent() {

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                        door.setOpen(true);
                    } else {
                        if (room.getName().equals("hall")){
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
        return smartHome;
    }
}
