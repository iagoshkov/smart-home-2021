package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.door.Door;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_OPEN;

public class DoorEventHandler {
    private SensorEvent event;
    private SmartHome smartHome;

    public DoorEventHandler(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public SmartHome handleDoorEvent() {

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        OpenDoorEventHandler openDoorEventHandler = new OpenDoorEventHandler(door, room, smartHome);
                        door = openDoorEventHandler.handleOpenDoorEvent();
                    } else {
                        CloseDoorEventHandler closeDoorEventHandler = new CloseDoorEventHandler(door,room, smartHome);
                        door = closeDoorEventHandler.handleCloseDoorEvent();
                    }
                }
            }
        }
        return smartHome;
    }
}
