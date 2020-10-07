package ru.sbt.mipt.oop.event_handlers;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.light.Light;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventHandlerTest {

    @Test
    void handleDoorEventChangesClosedDoorToOpened() {
        String tmpId = "123";
        Door door = new Door(false, tmpId);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "123");
        DoorEventHandler doorEventHandler = new DoorEventHandler(event, door);
        doorEventHandler.handleEvent();
        assertTrue(door.isOpen());
        doorEventHandler.handleEvent();
        assertTrue(door.isOpen());
    }

    @Test
    void handleDoorEventChangesOpenedDoorToClosed() {
        String tmpId = "123";
        Door door = new Door(false, tmpId);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "123");
        DoorEventHandler doorEventHandler = new DoorEventHandler(event, door);
        doorEventHandler.handleEvent();
        assertFalse(door.isOpen());
        doorEventHandler.handleEvent();
        assertFalse(door.isOpen());
    }
}