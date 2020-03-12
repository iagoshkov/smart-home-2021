package ru.sbt.mipt.oop.objects;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.eventhandler.EventDoorHandler;
import ru.sbt.mipt.oop.eventhandler.EventLightHandler;

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
        EventLightHandler handler = new EventLightHandler(smartHome);
        assertNull(handler.findLightByID("4"));
    }

    @Test
    void testFindLightByID() {
        Room room = new Room("room_1");
        room.addLights(Arrays.asList(new Light("4", false), new Light("8", false), new Light("9", false)));
        room.addDoors(Collections.singletonList(new Door(true, "4")));
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        EventLightHandler handler = new EventLightHandler(smartHome);
        Light light = handler.findLightByID("4");
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
        EventLightHandler handler = new EventLightHandler(smartHome);

        Room foundRoom = handler.findRoomByLight("5");
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
        EventDoorHandler handler = new EventDoorHandler(smartHome);

        Room foundRoom = handler.findRoomByDoor("4");
        assertEquals(foundRoom.getName(), room.getName());
    }

    @Test
    void testNotFindDoorByID() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectID = "5";
        EventDoorHandler handler = new EventDoorHandler(smartHome);
        assertNull(handler.findDoorByID(objectID));
    }

    @Test
    void testFindDoorByID() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectID = "1";
        EventDoorHandler handler = new EventDoorHandler(smartHome);
        assertEquals(handler.findDoorByID(objectID).getId(), objectID);
        assertFalse(handler.findDoorByID(objectID).isOpen());
    }
}