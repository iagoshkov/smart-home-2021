package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.command.SensorCommandSender;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class EventHallDoorHandler extends EventDoorHandler implements EventHandler {
    private final SensorCommandSender sensorCommandSender;

    public EventHallDoorHandler(SmartHome smartHome, SensorCommandSender sensorCommandSender) {
        super(smartHome);
        this.sensorCommandSender = sensorCommandSender;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED && findRoomByDoor(event.getObjectId()) != null && findRoomByDoor(event.getObjectId()).getName().equals("hall")) {
            handleHallDoorClosedEvent(event);
        }
    }

    private void turnOffLights() {
        smartHome.execute(light -> {
            if (light instanceof Light) {
                ((Light) light).setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, ((Light)light).getId());
                sensorCommandSender.sendCommand(command);
            }
        });
    }

    private void handleHallDoorClosedEvent(SensorEvent sensorEvent) {
        closeDoor(sensorEvent);
        turnOffLights();
    }
}
