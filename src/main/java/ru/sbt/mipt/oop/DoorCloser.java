package ru.sbt.mipt.oop;

public class DoorCloser {
    private final SmartHome smartHome;

    public DoorCloser(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private Room setLight(String id, boolean isOpen) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    door.setOpen(isOpen);
                    return room;
                }
            }
        }
        return null;
    }

    public void open(String id) {
        Room roomWhereOpened = this.setLight(id, true);
        if (roomWhereOpened != null)
            System.out.println("Door " + id + " in room " + roomWhereOpened.getName() + " was opened.");
    }

    public Room close(String id) {
        Room roomWhereClosed = this.setLight(id, false);
        if (roomWhereClosed != null)
            System.out.println("Door " + id + " in room " + roomWhereClosed.getName() + " was closed.");
        return roomWhereClosed;
    }

}
