package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.alarm.Alarm;

import java.util.Collection;

public class SmartHome {

    Collection<Room> rooms;


    public Collection<Room> getRooms() {
        return rooms;
    }

    public SmartHome(Collection<Room> rooms, Alarm alarm) {
        this.alarm = alarm;
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void setAlarm() {
        this.alarm = new Alarm(0);
    }

    private Alarm alarm;

    public Alarm getAlarm() {
        return alarm;
    }
}
