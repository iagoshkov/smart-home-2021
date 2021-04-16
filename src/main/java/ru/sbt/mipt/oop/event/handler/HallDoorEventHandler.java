package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.command.CommandProducer;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;

public class HallDoorEventHandler implements EventHandler {

    private final SmartHome smartHome;
    private final CommandProducer commandProducer;

    public HallDoorEventHandler(SmartHome smartHome, CommandProducer commandProducer) {
        this.smartHome = smartHome;
        this.commandProducer = commandProducer;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) return;

        String hallRoomName = "hall";
        String doorId = event.getObjectId();

        smartHome.execute(obj -> {
            if (obj instanceof Room room) {
                if (room.getName().equals(hallRoomName)) { // find out the hall room
                    room.execute(element -> {
                        if (element instanceof Door door) {
                            if (door.getId().equals(doorId)) { // find out the closed door (if it is in hall room)
                                commandProducer.produceCommand(smartHome);
                            }
                        }
                    });
                }
            }
        });
    }

}
