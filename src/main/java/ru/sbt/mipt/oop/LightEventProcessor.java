package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class LightEventProcessor implements EventProcessor {

    private final SmartHome smartHome;

    static final List<SensorEventType> lightEventTypes = Arrays.asList(LIGHT_ON, LIGHT_OFF);

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public List<CommandType> processEvent(SensorEvent event) {
        if (!lightEventTypes.contains(event.getType())) return new ArrayList<>();

        List<CommandType> commandTypes = new ArrayList<>();

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                CommandType command = handleEvent(event, room, light);
                if (command != null) {
                    commandTypes.add(command);
                }
            }
        }
        return commandTypes;
    }

    private CommandType handleEvent(SensorEvent event, Room room, Light light) {
        if (!light.getId().equals(event.getObjectId())) return null;

        switch (event.getType()) {
            case LIGHT_ON:
                light.setOn(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                break;
            case LIGHT_OFF:
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                break;
            default:
                // do nothing
        }
        return null;
    }

}
