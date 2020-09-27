package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.door.OpenedDoor;

public class OpenDoorEventHandler {

    private Door door;
    private Room room;
    private SmartHome smartHome;

    public OpenDoorEventHandler(Door door, Room room, SmartHome smartHome) {
        this.door = door;
        this.room = room;
        this.smartHome = smartHome;
    }

    public Door handleOpenDoorEvent() {
        door = new OpenedDoor(door).openedDoor();
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        return door;
    }
}
