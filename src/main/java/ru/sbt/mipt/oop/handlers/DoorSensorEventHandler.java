package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorSensorEventHandler implements SensorEventHandler {
    private final Collection<Room> rooms;
    private final SensorEvent event;

    public DoorSensorEventHandler(Collection<Room> rooms, SensorEvent event) {
        this.rooms = rooms;
        this.event = event;
    }

    @Override
    public void handleEvent() {
        // событие от двери
        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        handleDoorOpen(room, door);
                    } else {
                        handleDoorClose(room, door);
                    }
                }
            }
        }
    }

    private void handleDoorOpen(Room room, Door door) {
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    private void handleDoorClose(Room room, Door door) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        if (room.getName().equals("hall")) {
            for (Room homeRoom : rooms) {
                for (Light light : homeRoom.getLights()) {
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    sendCommand(command);
                }
            }
        }
    }

    private void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
