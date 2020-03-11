package ru.sbt.mipt.oop.objects;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeTest {

    @Test
    void testNotFindLightByID() {
        Room room = new Room("room_1");
        room.addLights(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)));
        room.addDoors(Collections.singletonList(new Door(false, "4")));
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        assertNull(smartHome.findLightByID("4"));
        assertNull(smartHome.findLightByID(smartHome.findRoomByLight("4"), "4"));
    }

    @Test
    void testFindLightByID() {
        Room room = new Room("room_1");
        room.addLights(Arrays.asList(new Light("4", false), new Light("8", false), new Light("9", false)));
        room.addDoors(Collections.singletonList(new Door(true, "4")));
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        Light light = smartHome.findLightByID("4");
        assertEquals(light.getId(), "4");
        assertFalse(light.isOn());

        light = smartHome.findLightByID(smartHome.findRoomByLight("4"), "4");
        assertEquals(light.getId(), "4");
        assertFalse(light.isOn());
    }

    @Test
    void testFindRoomByLight() {
        Room room = new Room("room_1");
        room.addLights(Collections.singletonList(new Light("4", false)));
        room.addDoors(Collections.singletonList(new Door(true, "4")));

        Room hall = new Room("hall");
        hall.addLights(Collections.singletonList(new Light("5", false)));
        hall.addDoors(Collections.singletonList(new Door(true, "5")));

        SmartHome smartHome = new SmartHome(Arrays.asList(room, hall));

        Room foundRoom = smartHome.findRoomByLight("5");
        assertEquals(foundRoom.getName(), hall.getName());
    }

    @Test
    void testFindRoomByDoor() {
        Room room = new Room("room_1");
        room.addLights(Collections.singletonList(new Light("4", false)));
        room.addDoors(Collections.singletonList(new Door(true, "4")));

        Room hall = new Room("hall");
        hall.addLights(Collections.singletonList(new Light("5", false)));
        hall.addDoors(Collections.singletonList(new Door(true, "5")));

        SmartHome smartHome = new SmartHome(Arrays.asList(room, hall));

        Room foundRoom = smartHome.findRoomByDoor("4");
        assertEquals(foundRoom.getName(), room.getName());
    }

    @Test
    void testNotFindDoorByID() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectID = "5";
        assertNull(smartHome.findDoorByID(objectID));
        assertNull(smartHome.findDoorByID(smartHome.findRoomByDoor(objectID), objectID));
    }

    @Test
    void testFindDoorByID() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectID = "1";
        assertEquals(smartHome.findDoorByID(objectID).getId(), objectID);
        assertEquals(smartHome.findDoorByID(smartHome.findRoomByDoor(objectID), objectID).getId(), objectID);

        assertFalse(smartHome.findDoorByID(objectID).isOpen());
        assertFalse(smartHome.findDoorByID(smartHome.findRoomByDoor(objectID), objectID).isOpen());
    }
}