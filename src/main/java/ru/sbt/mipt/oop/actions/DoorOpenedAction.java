package ru.sbt.mipt.oop.actions;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Room;
import ru.sbt.mipt.oop.devices.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;

public class DoorOpenedAction implements Action {
    private final SmartHome smartHome;
    private final SensorEvent event;

    public DoorOpenedAction(SmartHome smartHome, SensorEvent event) {
        this.smartHome = smartHome;
        this.event = event;
    }

    public void execute() {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    door.open();
                    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                }
            }
        }
    }
}
