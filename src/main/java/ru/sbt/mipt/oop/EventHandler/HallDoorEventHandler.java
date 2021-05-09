package ru.sbt.mipt.oop.EventHandler;

import ru.sbt.mipt.oop.CommandSender;
import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome.Door;
import ru.sbt.mipt.oop.SmartHome.Light;
import ru.sbt.mipt.oop.SmartHome.Room;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public HallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        System.out.println("Turning lights off at whole home");
        if(event.getType().equals(DOOR_CLOSED) && findRoomByDoorId(event.getObjectId()).getName().equals("hall")) {
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    CommandSender.sendCommand(command);
                }
            }
        }
    }

    private Room findRoomByDoorId(String doorId) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    return room;
                }
            }
        }
        throw new RuntimeException("Door with id=" + doorId + "is not found in home");
    }
}
