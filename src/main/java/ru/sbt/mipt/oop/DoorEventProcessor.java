package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    private final SmartHome smartHome;

    static final List<SensorEventType> doorEventTypes = Arrays.asList(DOOR_OPEN, DOOR_CLOSED);

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public List<CommandType> processEvent(SensorEvent event) {
        if (!doorEventTypes.contains(event.getType())) return new ArrayList<>();

        List<CommandType> commandTypes = new ArrayList<>();

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                CommandType command = handleEvent(smartHome, event, room, door);
                if (command != null) {
                    commandTypes.add(command);
                }
            }
        }
        return commandTypes;
    }

    private CommandType handleEvent(SmartHome smartHome, SensorEvent event, Room room, Door door) {
        if (!door.getId().equals(event.getObjectId())) return null;

        switch (event.getType()) {
            case DOOR_OPEN:
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                break;
            case DOOR_CLOSED:
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");

                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                String hallRoomName = "hall";
                if (room.getName().equals(hallRoomName)) {
                    return CommandType.LIGHT_OFF;
                }
                break;
            default:
                // do nothing
        }
        return null;
    }

}
