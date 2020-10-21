package ru.sbt.mipt.oop.event_handlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventHandlerTest {

    @Test
    void handleDoorEventChangesClosedDoorToOpened() {
        String tmpId = "123";
        Door door = new Door(false, tmpId);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "123");
        DoorEventHandler doorEventHandler = new DoorEventHandler();
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Door> doors = new ArrayList<>();
        doors.add(door);
        Room room1 = new Room(null, doors, "name");
        rooms.add(room1);
        SmartHome smartHome = new SmartHome(rooms);
        doorEventHandler.handleEvent(event, smartHome);
        assertTrue(door.isOpen());
        doorEventHandler.handleEvent(event, smartHome);
        assertTrue(door.isOpen());
    }

    @Test
    void handleDoorEventChangesOpenedDoorToClosed() {
        String tmpId = "123";
        Door door = new Door(false, tmpId);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "123");
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Door> doors = new ArrayList<>();
        doors.add(door);
        Room room1 = new Room(null, doors, "name");
        rooms.add(room1);
        SmartHome smartHome = new SmartHome(rooms);
        DoorEventHandler doorEventHandler = new DoorEventHandler();
        doorEventHandler.handleEvent(event, smartHome);
        assertFalse(door.isOpen());
        doorEventHandler.handleEvent(event, smartHome);
        assertFalse(door.isOpen());
    }
}