package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.door.Door;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler{
    private final SensorEvent event;
    private final Door door;

    public DoorEventHandler(SensorEvent event, Door door) {
        this.event = event;
        this.door = door;
    }

    @Override
    public void handleEvent() {
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                door.setOpen(true);
            } else {
                door.setOpen(false);
            }
        }
    }
}

