package ru.sbt.mipt.oop;

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
