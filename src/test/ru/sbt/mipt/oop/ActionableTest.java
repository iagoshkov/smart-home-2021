package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ActionableTest {

    @Test
    void testNotFindLightByID() {
        Room room = new Room("room_1");
        room.addLights(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)));
        room.addDoors(Collections.singletonList(new Door(false, "4")));
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        AtomicReference<Light> light = new AtomicReference<>();
        smartHome.execute(lightCandidate -> {
            if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals("4")) {
                light.set((Light) lightCandidate);
            }
        });
        assertNull(light.get());
    }

    @Test
    void testFindLightByID() {
        Room room = new Room("room_1");
        room.addLights(Arrays.asList(new Light("4", false), new Light("8", false), new Light("9", false)));
        room.addDoors(Collections.singletonList(new Door(true, "4")));
        SmartHome smartHome = new SmartHome(Collections.singletonList(room));
        AtomicReference<Light> light = new AtomicReference<>();
        smartHome.execute(lightCandidate -> {
            if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals("4")) {
                light.set((Light) lightCandidate);
            }
        });
        assertEquals(light.get().getId(), "4");
        assertFalse(light.get().isOn());
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
        AtomicReference<Room> r = new AtomicReference<>();
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(lightCandidate -> {
                    if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals("5")) {
                        r.set((Room) roomCandidate);
                    }
                });
            }
        });

        Room foundRoom = r.get();
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

        AtomicReference<Room> r = new AtomicReference<>();
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(doorCandidate -> {
                    if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals("4")) {
                        r.set((Room) roomCandidate);
                    }
                });
            }
        });

        Room foundRoom = r.get();
        assertEquals(foundRoom.getName(), room.getName());
    }

    @Test
    void testNotFindDoorByID() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectID = "5";
        AtomicReference<Door> door = new AtomicReference<>();
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(objectID)) {
                door.set((Door) doorCandidate);
            }
        });
        assertNull(door.get());
    }

    @Test
    void testFindDoorByID() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectID = "1";
        AtomicReference<Door> door = new AtomicReference<>();
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(objectID)) {
                door.set((Door) doorCandidate);
            }
        });
        assertEquals(door.get().getId(), objectID);
        assertFalse(door.get().isOpen());
    }
}