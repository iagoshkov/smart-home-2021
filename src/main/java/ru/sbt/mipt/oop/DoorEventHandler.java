package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class DoorEventHandler implements EventHandler{

    DoorEventHandler(SmartHome smartHome){
        this.smartHome = smartHome;
        this.commandPrinter = new PrintCommandSender();
    }

    @Override
    public Action handleEvent(SensorEvent event) {
        return ((component -> {
            if (component instanceof Door){
                Door door = (Door)component;
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " was opened.");
                    }
                    if (event.getType() == DOOR_CLOSED){
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " was closed.");
                    }
                }
            }
        }));

    }

    SmartHome smartHome;
    PrintCommandSender commandPrinter;
}
