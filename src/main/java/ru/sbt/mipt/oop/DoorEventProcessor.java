package ru.sbt.mipt.oop;

//import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
//import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (isDoorEvent(event)) {
            smartHome.execute(component -> {
                if (component instanceof Door) {
                    Door door = (Door) component;
                    if (door.getId().equals(event.getObjectId())) {
                        door.setOpen(event.getType() == DOOR_OPEN);
                        String doorState = (event.getType() == DOOR_OPEN) ? "opened." : "closed.";
                        System.out.println("Door " + door.getId() + " was " + doorState);
                    }
                }
            });
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
