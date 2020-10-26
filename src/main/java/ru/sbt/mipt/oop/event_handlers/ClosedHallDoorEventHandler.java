package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

public class ClosedHallDoorEventHandler implements GeneralEvent{

    public ClosedHallDoorEventHandler() {
    }

    @Override
    public void handleEvent(SensorEvent event, SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(false);
            }
        }
        System.out.println("All lights were turned off.");
    }
}
