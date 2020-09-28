package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SmartHome {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void processDoorEvent(SensorEvent event) {
        for (Room room : this.getRooms()) {
            Door door = room.getDoor(event.getObjectId());
            if (door != null) {
                room.changeDoor(door.getId(), (event.getType() == DOOR_OPEN));
                if (room.getName().equals("hall") && event.getType() == DOOR_CLOSED) {
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    this.setAllLights(false, CommandType.LIGHT_OFF);
                }
            }
        }
    }

    public void processLightEvent(SensorEvent event) {
        for (Room room : this.getRooms()) {
            Light light = room.getLight(event.getObjectId());
            if (light != null) {
                room.changeLight(event.getObjectId(), (event.getType() == LIGHT_ON));
            }
        }
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setAllLights(boolean isOn, CommandType lightCommand) {
        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                light.setOn(isOn);
                if (lightCommand != null) {
                    SensorCommand command = new SensorCommand(lightCommand, light.getId());
                    SensorCommand.sendCommand(command);
                }
            }
        }
    }


}
