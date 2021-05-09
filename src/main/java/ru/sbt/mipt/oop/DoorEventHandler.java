package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventHandler implements EventHandler{
    private final SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    public void handleEvent(SensorEvent event) {
        // событие от двери
        //System.out.println("Handling door event");
        smartHome.execute((object) -> {
            if(object instanceof Door) {
                //System.out.println("A");
                Door door = (Door) object;
                if(door.getId().equals(event.getObjectId())) {
                    if(event.getType().equals(DOOR_OPEN)) {
                        door.setOpen(true);
                        System.out.println("door " + door.getId() + " was set OPEN");
                    }
                    if(event.getType().equals(DOOR_CLOSED)) {
                        door.setOpen(false);
                        System.out.println("door " + door.getId() + " was set CLOSED");
                    }
                }
            }
        });
        /*for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            for (Room homeRoom : smartHome.getRooms()) {
                                for (Light light : homeRoom.getLights()) {
                                    light.setOn(false);
                                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                    CommandSender.sendCommand(command);
                                }
                            }
                        }
                    }
                }
            }
        }*/
    }
}
