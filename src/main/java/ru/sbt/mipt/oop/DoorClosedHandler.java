package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class DoorClosedHandler implements EventHandler {

    @Override
    public List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) return null;

        List<CommandType> commandTypes = new ArrayList<>();

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (!door.getId().equals(event.getObjectId())) continue;

                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");

                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                CommandType commandType = DoorClosedCommandGenerator.generateCommand(room);
                if (commandType != null) {
                    commandTypes.add(commandType);
                }
            }
        }
        return commandTypes;
    }

}
