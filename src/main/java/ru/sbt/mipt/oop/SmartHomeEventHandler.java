package ru.sbt.mipt.oop;

public class SmartHomeEventHandler implements EventHandler {

    private final SmartHome smartHome;
    private final HomeControl homeControl;

    public SmartHomeEventHandler(HomeControl homeInteraction, SmartHome smartHome) {
        this.homeControl = homeInteraction;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        switch (event.getType()) {
            case LIGHT_ON -> handleLightOn(event.getObjectId());
            case LIGHT_OFF -> handleLightOff(event.getObjectId());
            case DOOR_OPEN -> handleDoorOpen(event.getObjectId());
            case DOOR_CLOSED -> handleDoorClosed(event.getObjectId());
        }
    }

    private static class RoomLight {
        Room room;
        Light light;
        RoomLight(Room room, Light light) {
            this.room = room;
            this.light = light;
        }
    }

    private static class RoomDoor {
        Room room;
        Door door;
        RoomDoor(Room room, Door door) {
            this.room = room;
            this.door = door;
        }
    }

    private RoomLight findRoomLight(String lightId) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)) {
                    return new RoomLight(room, light);
                }
            }
        }
        throw new RuntimeException("Light ID not found");
    }

    private RoomDoor findRoomDoor(String doorId) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    return new RoomDoor(room, door);
                }
            }
        }
        throw new RuntimeException("Door ID not found");
    }

    private void handleLightOn(String lightId) {
        RoomLight roomLight = findRoomLight(lightId);
        roomLight.light.setOn(true);
        System.out.println("Light " + roomLight.light.getId() +
                " in room " + roomLight.room.getName() +
                " was turned on.");
    }

    private void handleLightOff(String lightId) {
        RoomLight roomLight = findRoomLight(lightId);
        roomLight.light.setOn(false);
        System.out.println("Light " + roomLight.light.getId() +
                " in room " + roomLight.room.getName() +
                " was turned off.");
    }

    private void handleDoorOpen(String doorId) {
        RoomDoor roomDoor = findRoomDoor(doorId);
        roomDoor.door.setOpen(true);
        System.out.println("Door " + roomDoor.door.getId() +
                " in room " + roomDoor.room.getName() +
                " was opened.");
    }

    private void handleDoorClosed(String doorId)  {
        RoomDoor roomDoor = findRoomDoor(doorId);
        roomDoor.door.setOpen(false);
        System.out.println("Door " + roomDoor.door.getId() +
                " in room " + roomDoor.room.getName() +
                " was closed.");

        if (roomDoor.room.getName().equals("hall")) {
            for (Room homeRoom : smartHome.getRooms()) {
                for (Light light : homeRoom.getLights()) {
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    homeControl.sendCommand(command);
                }
            }
        }
    }
}
