package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.actions.DoorClosedAction;
import ru.sbt.mipt.oop.actions.DoorOpenAction;
import ru.sbt.mipt.oop.actions.LightOnAction;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.door.Door;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_OPEN;

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
                            DoorOpenAction doorOpenAction = new DoorOpenAction(door);
                            smartHome.execute(doorOpenAction);
                        } else if (event.getType() == DOOR_CLOSED) {
                            if (room.getName().equals("hall")) {
                                ClosedHallDoorEvent closedHallDoorEvent = new ClosedHallDoorEvent();
                                closedHallDoorEvent.hallDoorClosed(smartHome);
                            } else {
                                DoorClosedAction doorClosedAction = new DoorClosedAction(door);
                                smartHome.execute(doorClosedAction);
                            }
                        }
                    }
                }
            }
        }
    }
}
