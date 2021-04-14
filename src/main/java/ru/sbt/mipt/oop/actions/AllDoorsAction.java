package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Door;

import java.util.ArrayList;
import java.util.List;

public class AllDoorsAction implements Action {

    private final List<Door> doors = new ArrayList<>();

    public List<Door> getDoors() {
        return doors;
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Door door) {
            doors.add(door);
        }
    }

}
