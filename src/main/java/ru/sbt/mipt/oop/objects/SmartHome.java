package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.signaling.Signaling;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private Signaling signaling = null;

    public SmartHome() {
        this.rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addSignaling(Signaling signaling) {
        this.signaling = signaling;
    }

    public Signaling getSignaling() {
        return this.signaling;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
        rooms.forEach(room -> room.execute(action));
    }
}
