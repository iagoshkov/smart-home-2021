package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {

        if (event.getType() != DOOR_CLOSED) {
            return;
        }

        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;
                if (room.getName().equals("hall")) {
                    room.execute(hallComponent -> {
                        if (hallComponent instanceof Door) {
                            Door door = (Door) hallComponent;
                            if (door.getId().equals(event.getObjectId())) {
                                door.setOpen(false);
                                turnOffAllLights(smartHome);
                            }
                        }
                    });
                }
            }
        });
    }

    private void turnOffAllLights(SmartHome smartHome) {
        smartHome.execute(component -> {
            if (component instanceof Light) {
                Light light = (Light) component;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandTransmitter.sendCommand(command);
            }
        });
    }
}

