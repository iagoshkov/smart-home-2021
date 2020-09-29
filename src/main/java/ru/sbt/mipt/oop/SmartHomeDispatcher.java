package ru.sbt.mipt.oop;
import ru.sbt.mipt.oop.actions.*;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.Room;
import ru.sbt.mipt.oop.devices.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

public class SmartHomeDispatcher {
    private final SmartHome smartHome;

    public SmartHomeDispatcher(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public SmartHome getSmartHome() {
        return smartHome;
    }

    public void start() {
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            handleEvent(event);
            event = getNextSensorEvent();
        }
    }

    private void handleEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        Action action;
        switch (event.getType()) {
            case LIGHT_ON:
                 action = new LightOnAction(smartHome, event);
                action.execute();
                break;
            case LIGHT_OFF:
                action = new LightOffAction(smartHome, event);
                action.execute();
                break;
            case DOOR_OPEN:
                action = new DoorOpenedAction(smartHome, event);
                action.execute();
                break;
            case DOOR_CLOSED:
                action = new DoorClosedAction(smartHome, event);
                action.execute();
                for (Room room : smartHome.getRooms()) {
                    for (Door door : room.getDoors()) {
                        if (door.getId().equals(event.getObjectId()) && room.getName().equals("hall")) {
                            for (Room homeRoom : smartHome.getRooms()) {
                                for (Light light : homeRoom.getLights()) {
                                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                    sendCommand(command);
                                    action = new LightOffAction(smartHome, event);
                                    action.execute();
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    private void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
