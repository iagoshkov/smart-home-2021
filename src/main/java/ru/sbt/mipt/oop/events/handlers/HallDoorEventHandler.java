package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.HallDoorCloseAction;
import ru.sbt.mipt.oop.actions.LightOffAction;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.sensor.command.CommandType;
import ru.sbt.mipt.oop.sensor.command.SensorCommand;
import ru.sbt.mipt.oop.sensor.command.senders.CommandSender;
import ru.sbt.mipt.oop.sensor.command.senders.ICommandSender;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements IEventHandler {
    public HallDoorEventHandler() {

    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Door) {
                Door door = (Door) homeComponent;
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_CLOSED) {
//                        lightOffAllRooms(smartHome);
                        Action action = new HallDoorCloseAction(event.getObjectId());
                        smartHome.execute(action);
                    }
                }

            }
        });
    }
}
