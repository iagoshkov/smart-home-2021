package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class Door {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    void processEvent(SensorEvent event) {
        if (id.equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                doDoorOpen();
            } else if (event.getType() == DOOR_CLOSED) {
                doDoorClose();
            }
        }
    }

    private void doDoorOpen() {
        isOpen = true;
        System.out.println("Door " + id + " was opened.");
    }

    private void doDoorClose() {
        isOpen = false;
        System.out.println("Door " + id + " was closed.");
    }
}
