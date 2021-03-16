package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.sensors.SensorCommand;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.Collection;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_OPEN;

public class DoorSensorEventHandler implements SensorEventHandler {
    private final Collection<Room> rooms;
    private final SensorEvent event;
    private final SmartHome smartHome;

    public DoorSensorEventHandler(SmartHome smartHome, SensorEvent event) {
        this.rooms = smartHome.getRooms();
        this.event = event;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent() {
        // событие от двери
        if (event.getType() != DOOR_OPEN && event.getType() != DOOR_CLOSED) {
            return;
        }

        boolean openResult = (event.getType() == DOOR_OPEN);
        String eventId = event.getObjectId();
        
        Action lightOffAction = (o)->{
            if (o instanceof Light) {
                Light light = (Light) o;
                light.setOn(false);
            }
        };
        
        Action doorAction = (o)-> {
            if (o instanceof Room) {
                Room room = (Room) o;
                if (room.getName().equals("hall")) {
                    smartHome.doAction(lightOffAction);
                }
            }
            if (o instanceof Door) {
                Door door = (Door) o;
                if (door.getId().equals(eventId)) {
                    door.setOpen(openResult);
                    System.out.print("Door " + door.getId() + " in room " + " was");
                    System.out.println(openResult ? " opened." : " closed.");
                }
            }
        };

        smartHome.doAction(doorAction);
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
        handleHallDoorClose(room, door);
    }

    private void handleHallDoorClose(Room room, Door door) {
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