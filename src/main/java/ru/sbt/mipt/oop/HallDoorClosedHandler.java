package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HallDoorClosedHandler implements EventHandler {

    public List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event) {
        String hallRoomName = "hall";

        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals(hallRoomName)) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        return Collections.singletonList(CommandType.LIGHT_OFF);
                    }
                }
            }
        }
        return new ArrayList<>();
    }

}
