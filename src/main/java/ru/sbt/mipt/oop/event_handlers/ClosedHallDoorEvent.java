package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

import java.util.ArrayList;
import java.util.List;

public class ClosedHallDoorEvent {

    public ClosedHallDoorEvent() {
    }

    public void hallDoorClosed(SmartHome smartHome){
        List<SensorCommand> returnList = new ArrayList<>();
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                returnList.add(command);
            }
        }
        System.out.println("Door in hall was closed. All lights were turned off.");
    }
}
