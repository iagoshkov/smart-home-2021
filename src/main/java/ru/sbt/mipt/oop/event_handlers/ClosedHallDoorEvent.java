package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;
import ru.sbt.mipt.oop.light.TurnedOffLight;

import java.util.ArrayList;
import java.util.List;

public class ClosedHallDoorEvent {

    public ClosedHallDoorEvent(SmartHome smartHome) {
    }

    public List<SensorCommand> hallDoorClosed(SmartHome smartHome){
        List<SensorCommand> returnList = new ArrayList<>();
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light = new TurnedOffLight(light).turnedOffLight();
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                returnList.add(command);
            }
        }
        return returnList;
    }
}
