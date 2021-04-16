package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;

public record CloseDoorAction(String doorId) implements Action {
    @Override
    public void apply(Object obj) {
        if (obj instanceof Room room) {
            room.execute(element -> {
                if (element instanceof Door door) {
                    if (door.getId().equals(doorId)) {
                        door.setOpen(false);
                        System.out.println("Door " + doorId + " in room " + room.getName() + " was closed.");
                    }
                }
            });
        }
    }
}
