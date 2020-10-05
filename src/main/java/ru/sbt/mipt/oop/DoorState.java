package ru.sbt.mipt.oop;

public class DoorState {
    public static boolean handleEvent(SmartHome smartHome, SensorEvent event) {
        boolean _check = false;
        for (Room room : smartHome.getRooms()) {
            boolean _check1 = room.handleDoorEvent(smartHome, event);
            if (_check1 == true){
                _check = _check1;
            }
        }
        return _check;
    }
}

