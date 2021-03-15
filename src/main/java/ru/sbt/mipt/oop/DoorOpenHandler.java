package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class DoorOpenHandler implements EventHandler {

    @Override
    public List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_OPEN) return null;

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (!door.getId().equals(event.getObjectId())) continue;

                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            }
        }
        return new ArrayList<>();
    }

}
