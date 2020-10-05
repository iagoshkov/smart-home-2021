package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.door.ClosedDoor;
import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.light.Light;
import ru.sbt.mipt.oop.light.TurnedOffLight;

public class CloseDoorEventHandler {

    private Door door;
    private Room room;
    private SmartHome smartHome;

    public CloseDoorEventHandler(Door door, Room room, SmartHome smartHome) {
        this.door = door;
        this.room = room;
        this.smartHome = smartHome;
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    public Door handleCloseDoorEvent() {
        door = new ClosedDoor(door).closedDoor();
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        if (room.getName().equals("hall")) {
            ClosedHallDoorEvent closedHall = new ClosedHallDoorEvent(smartHome);
            for (SensorCommand command: closedHall.hallDoorClosed(smartHome)
                 ) {
                sendCommand(command);
            }
        }
        return door;
    }
}
