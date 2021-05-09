package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventHandler implements EventHandler{
    private final SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        // событие от двери
        //System.out.println("Handling door event");
        smartHome.execute((object) -> {
            if(object instanceof Door) {
                //System.out.println("A");
                Door door = (Door) object;
                if(door.getId().equals(event.getObjectId())) {
                    if(event.getType().equals(DOOR_OPEN)) {
                        door.setOpen(true);
                        System.out.println("door " + door.getId() + " was set OPEN");
                    }
                    if(event.getType().equals(DOOR_CLOSED)) {
                        door.setOpen(false);
                        System.out.println("door " + door.getId() + " was set CLOSED");
                    }
                }
            }
        });
        /*for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    }
                    if (event.getType() == DOOR_CLOSED) {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    }
                }
            }
        }*/
    }
}
