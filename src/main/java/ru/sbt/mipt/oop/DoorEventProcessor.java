package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventProcessor implements EventHandler{
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (isDoorEvent(event)) {
            Action action = object -> {
                if (! (object instanceof Door)) { return; }

                Door asDoor = (Door) object;

                if (asDoor.getId().equals(event.getObjectId())) {
                    asDoor.setOpen(event.getType() == DOOR_OPEN);
                }
            };
            smartHome.execute(action);
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
