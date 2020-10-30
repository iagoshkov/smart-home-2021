package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_CLOSED;

public class ClosedHallDoorEventHandler implements GeneralEvent{

    public ClosedHallDoorEventHandler() {
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_CLOSED){
            for (Room room : smartHome.getRooms()) {
                if (room.getName().equals("hall")){
                    for (Light light : room.getLights()) {
                        light.setOn(false);
                    }
                    System.out.println("All lights were turned off.");
                }
            }
        }
    }
}
