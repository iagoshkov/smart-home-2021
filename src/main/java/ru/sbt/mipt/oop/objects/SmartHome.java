package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.Actionable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void execute(Consumer<Object> action) {
        rooms.forEach(room -> room.execute(action));
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Light findLightByID(Room room, String id) {
        if (room == null) return null;
        AtomicReference<Light> light = new AtomicReference<>();
        room.execute(lightCandidate -> {
            if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals(id)) {
                light.set((Light) lightCandidate);
            }
        });
        return light.get();
    }

    public Light findLightByID(String id) {
        AtomicReference<Light> light = new AtomicReference<>();
        this.execute(lightCandidate -> {
            if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals(id)) {
                light.set((Light) lightCandidate);
            }
        });
        return light.get();
    }

    public Room findRoomByLight(String id) {
        AtomicReference<Room> r = new AtomicReference<>();
        this.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(lightCandidate -> {
                    if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals(id)) {
                        r.set((Room) roomCandidate);
                    }
                });
            }
        });
        return r.get();
    }

    public Room findRoomByDoor(String id) {
        AtomicReference<Room> r = new AtomicReference<>();
        this.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(doorCandidate -> {
                    if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(id)) {
                        r.set((Room) roomCandidate);
                    }
                });
            }
        });
        return r.get();
    }

    public Door findDoorByID(Room room, String id) {
        if (room == null) return null;
        AtomicReference<Door> door = new AtomicReference<>();
        room.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(id)) {
                door.set((Door) doorCandidate);
            }
        });
        return door.get();
    }

    public Door findDoorByID(String id) {
        AtomicReference<Door> door = new AtomicReference<>();
        this.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(id)) {
                door.set((Door) doorCandidate);
            }
        });
        return door.get();
    }
}
