package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

public class ClosedHallDoorEvent {

    public ClosedHallDoorEvent() {
    }

    public void hallDoorClosed(SmartHome smartHome){
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
            }
        }
        System.out.println("Door in hall was closed. All lights were turned off.");
    }
}
