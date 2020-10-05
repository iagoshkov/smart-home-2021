package ru.sbt.mipt.oop;

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

    public boolean handleEvent(SmartHome smartHome, SensorEvent event, String roomName) {
        boolean _check = false;
        if (this.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                this.setOpen(true);
                System.out.println("Door " + this.getId() + " in room " + roomName + " was opened.");
            } else {
                this.setOpen(false);
                System.out.println("Door " + this.getId() + " in room " + roomName + " was closed.");
                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                if (roomName.equals("hall")) {
                    smartHome.turnOffAllHouseLight(this);
                    _check = true;
                }
            }
        }
        return _check;
    }
}
