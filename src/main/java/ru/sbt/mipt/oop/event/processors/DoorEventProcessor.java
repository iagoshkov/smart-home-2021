package ru.sbt.mipt.oop.event.processors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commands.CommandSenderImpl;


public class DoorEventProcessor implements EventProcessor {
    private final SmartHome home;

    public DoorEventProcessor(SmartHome home) {
        this.home = home;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if(!isValidEvent(event.getType())) return;

        for (Room room : home.getRooms()) {
            Door door = room.getDoor(event.getObjectId());
            boolean isOpen = event.getType() == SensorEventType.DOOR_OPEN;
            if(door != null)
                closeOrOpenDoor(door, room, isOpen);
        }
    }

    private boolean isValidEvent(SensorEventType type) {
        return type == SensorEventType.DOOR_CLOSED || type == SensorEventType.DOOR_OPEN;
    }

    private void closeOrOpenDoor(Door door, Room room, boolean isOpen) {
        door.setOpen(isOpen);
        printMessageCloseOrOpenDoor(door, room, isOpen);
        if(room.getName().equals("hall")) {
            processForDoorInHall(isOpen);
        }
    }

    private void printMessageCloseOrOpenDoor(Door door, Room room, boolean isOpen) {
        String message = "Door " + door.getId() + " in room " + room.getName() + " was ";
        message += isOpen ? "opened." : "off.";
        System.out.println(message);
    }

    private void processForDoorInHall(boolean isOpen) {
        if(!isOpen) {
            LightEventProcessor processor = new LightEventProcessor(home, new CommandSenderImpl());
            processor.turnOffAllLights();
        }
    }
}