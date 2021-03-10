package ru.sbt.mipt.oop;

public class LightSwitcher {
    private final SmartHome smartHome;

    public LightSwitcher(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private Room setLight(String id, boolean isOn) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id)) {
                    light.setOn(isOn);
                    return  room;
                }
            }
        }
        return null;
    }

    public void turnOn(String id) {
        Room roomWhereTurned = this.setLight(id, true);
        if (roomWhereTurned != null)
            System.out.println("Light " + id + " in room " + roomWhereTurned.getName() + " was turned on.");
    }

    public void turnOff(String id) {
        Room roomWhereTurned = this.setLight(id, false);
        if (roomWhereTurned != null)
            System.out.println("Light " + id + " in room " + roomWhereTurned.getName() + " was turned off.");
    }


    public void turnOffAll() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
            }
        }
    }
}
