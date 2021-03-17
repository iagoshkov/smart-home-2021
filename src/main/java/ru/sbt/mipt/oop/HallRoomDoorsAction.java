package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class HallRoomDoorsAction implements Action {

    private final String hallRoomName;
    private List<Door> doors = new ArrayList<>();

    HallRoomDoorsAction(String hallRoomName) {
        this.hallRoomName = hallRoomName;
    }

    public List<Door> getDoors() {
        return doors;
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Room room) {
            if (room.getName().equals(hallRoomName)) {
                AllDoorsAction allDoorsAction = new AllDoorsAction();
                room.execute(allDoorsAction);
                doors = allDoorsAction.getDoors();
            }
        }
    }
}
