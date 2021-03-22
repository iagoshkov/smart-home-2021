package ru.sbt.mipt.oop;

public class DoorOpenHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (!door.getId().equals(event.getObjectId())) continue;

                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            }
        }
    }

}
