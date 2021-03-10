package ru.sbt.mipt.oop;

import java.util.HashMap;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class SmartHome {
    private final HashMap<String, Room> rooms;
    private final HashMap<String, String> lights = new HashMap<>();
    private final HashMap<String, String> doors = new HashMap<>();

    public SmartHome() {
        rooms = new HashMap<>();
    }

    public SmartHome(HashMap<String, Room> rooms) {
        this.rooms = rooms;

        for (Room room : rooms.values()) {
            connectDoorsAndRoom(room);
            connectLightsAndRoom(room);
        }
    }

    private void connectLightsAndRoom(Room room) {
        for (Door door : room.getDoors().values()) {
            doors.put(door.getId(), room.getName());
        }
    }

    private void connectDoorsAndRoom(Room room) {
        for (Light light : room.getLights().values()) {
            lights.put(light.getId(), room.getName());
        }
    }

    public void addRoom(Room room) {
        rooms.put(room.getName(), room);
        connectDoorsAndRoom(room);
        connectLightsAndRoom(room);
    }

    public void lightEvent(SensorEvent event) {
        // событие от источника света
        Room room = rooms.get(lights.get(event.getObjectId()));
        Light light = room.getLights().get(event.getObjectId());

        if (event.getType() == LIGHT_ON) {
            light.setOn(true);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        } else {
            light.setOn(false);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        }
    }

    public void doorEvent(SensorEvent event) {
        // событие от двери
        Room room = rooms.get(doors.get(event.getObjectId()));
        Door door = room.getDoors().get(event.getObjectId());

        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
            if (room.getName().equals("hall")) {
                turnOffEverything();
            }
        }
    }

    public void turnOffEverything() {
        for (Room homeRoom : rooms.values()) {
            for (Light light : homeRoom.getLights().values()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

    public HashMap<String, Room> getRooms() {
        return rooms;
    }

    public HashMap<String, String> getDoors() {
        return doors;
    }

    public HashMap<String, String> getLights() {
        return lights;
    }
}
