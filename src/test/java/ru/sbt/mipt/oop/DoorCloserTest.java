package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorCloserTest {
    Room kitchen = new Room(Arrays.asList(),
            Arrays.asList(new Door(true, "1"), new Door(false, "2")),
            "kitchen");
    SmartHome smartHome = new SmartHome(Arrays.asList(kitchen));
    DoorCloser doorCloser = new DoorCloser(smartHome);

    private Door getDoorById(SmartHome smartHome, String id) {
        for (Room room: smartHome.getRooms()) {
            for (Door door: room.getDoors()) {
                if (door.getId().equals(id)) {
                    return door;
                }
            }
        }
        return null;
    }

    @Test
    public void closeOpenedDoor() {
        doorCloser.close("1");

        assertEquals(false, getDoorById(smartHome, "1").isOpen());
    }

    @Test
    public void openOpenedDoor() {
        doorCloser.open("1");

        assertEquals(true, getDoorById(smartHome, "1").isOpen());
    }

    @Test
    public void openClosedDoor() {
        doorCloser.open("2");

        assertEquals(true, getDoorById(smartHome, "2").isOpen());
    }
}