package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements IEventHandler {
    public DoorEventHandler() {

    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen();
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    }
                    if (event.getType() == DOOR_CLOSED) {
                        door.setClosed();
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    }
                }
            }
        }
    }
}
