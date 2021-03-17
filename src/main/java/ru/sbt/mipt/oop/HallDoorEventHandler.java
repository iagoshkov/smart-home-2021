package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class HallDoorEventHandler implements EventHandler{

    HallDoorEventHandler(SmartHome smartHome){
        this.smartHome = smartHome;
        this.commandPrinter = new PrintCommandSender();
    }

    @Override
    public Action handleEvent(SensorEvent event) {
        return ((component -> {
            if (component instanceof Door){
                Door door = (Door)component;
                if (!SmartHomeHelpers.isHallDoor(smartHome, door.getId())){
                    return;
                }
                if (!door.getId().equals(event.getObjectId())) {
                    return;
                }
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " was opened.");
                    }
                    if (event.getType() == DOOR_CLOSED){
                        door.setOpen(false);
                        System.out.println("Door (hall) " + door.getId() + " was closed.");
                        smartHome.execute((innerComponent)->{
                            if (innerComponent instanceof Light){
                                Light light = (Light)innerComponent;
                                System.out.println("Light " + light.getId() + " was turned off.");
                                light.setOn(false);
                            }
                        });
                    }
            }
        }));
    }

    SmartHome smartHome;
    PrintCommandSender commandPrinter;
}
