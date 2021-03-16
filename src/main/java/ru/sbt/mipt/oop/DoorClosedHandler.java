package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class DoorClosedHandler implements EventHandler {

    @Override
    public List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) return null;

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (!door.getId().equals(event.getObjectId())) continue;

                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            }
        }
        return new ArrayList<>();
    }

}
