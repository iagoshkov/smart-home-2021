package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.utils.Searher;

public class DoorEventProcessor implements EventProcessor {
    private final SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private boolean isHallDoor(String id) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id) && room.getName().equals("hall")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void processEvent(SensorEvent event) {
        Door targetDoor = (new Searher(smartHome)).findDoor(event.getObjectId());
        if (targetDoor != null) {
            updateDoorState(targetDoor, getDoorState(event));

            if (isHallDoor(targetDoor.getId())) {
                CommandSender sender = new CommandSender(smartHome);
                sender.sendAllLight();
                (new LightEventProcessor(smartHome)).processEvent(new SensorEvent(SensorEventType.LIGHT_OFF, "all"));
            }
        }
    }

    private void updateDoorState(Door door, boolean newState) {
        door.setOpen(newState);
        System.out.println("Door " + door.getId() + " was " + (newState ? "opened." : "closed."));
    }

    private boolean getDoorState(SensorEvent event){
        return event.getType().equals(SensorEventType.DOOR_OPEN);
    }

}
