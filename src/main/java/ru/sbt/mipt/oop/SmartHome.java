package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }
    //this function has responsibility to change information about class
    public void addRoom(Room room) {
        rooms.add(room);
    }
    //this function has second responsibility to et information about class
    /*
    public Collection<Room> getRooms() {
        return rooms;
    }
    */
}
//we need other class to separate responsibility about getting information (SRP)
public class SmartHomeInformation extends SmartHome{
    public Collection<Room> getRooms() {
        return rooms;
    }  
}
