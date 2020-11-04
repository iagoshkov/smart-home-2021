package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.actions.*;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class EventManager {
    SmartHome smartHome;

    public EventManager() {
    }

    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

    public void processEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        for (Room room : smartHome.getRooms()) {
            if (event.getType() == DOOR_OPEN) {
                Action action = new DoorOpenAction(event.getObjectId());
                room.execute(action);
            }

            if (event.getType() == DOOR_CLOSED) {
                Action action = new DoorCloseAction(event.getObjectId());
                room.execute(action);
                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                if (room.getName().equals("hall")) {
                    lightOffAllRooms();
                }
            }

            if (event.getType() == LIGHT_ON) {
                Action action = new LightOnAction(event.getObjectId());
                room.execute(action);
            }

            if (event.getType() == LIGHT_OFF) {
                Action action = new LightOffAction(event.getObjectId());
                room.execute(action);
            }
        }
    }

    private void lightOffAllRooms() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (HomeComponent component : homeRoom.getComponents()) {
                if(component instanceof Light){
                    Light light = (Light) component;
                    light.setOff();
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    sendCommand(command);
                }
            }
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
