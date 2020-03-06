package ru.sbt.mipt.oop;

public class DoorSensorEventHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public DoorSensorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!isDoorEvent(event)) return;

        Room room = findRoom(event);
        Door door = findDoor(event);
        if (room == null || door == null) return;

        boolean setDoorOpen = event.getType() == SensorEventType.DOOR_OPEN;
        door.setOpen(setDoorOpen);
        logEvent(room, door, setDoorOpen ? "opened" : "closed");

        if ("hall".equals(room.getName())) {
            new LightSensorController(smartHome).turnOffAllLights();
        }
    }

    private void logEvent(Room room, Door door, String setDoor) {
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + setDoor + ".");
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == SensorEventType.DOOR_OPEN || event.getType() == SensorEventType.DOOR_CLOSED;
    }

    private Room findRoom(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    return room;
                }
            }
        }
        return null;
    }

    private Door findDoor(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    return door;
                }
            }
        }
        return null;
    }
}
