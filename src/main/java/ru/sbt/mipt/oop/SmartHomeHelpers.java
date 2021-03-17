package ru.sbt.mipt.oop;

public class SmartHomeHelpers {
    static boolean isHallDoor(SmartHome smartHome, String id){
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if ((door.getId().equals(id)) && (room.getName().equals("hall"))){
                    return true;
                }
            }
        }
        return false;
    }
}
