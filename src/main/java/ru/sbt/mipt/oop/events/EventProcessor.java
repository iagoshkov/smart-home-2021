package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.actions.*;
import ru.sbt.mipt.oop.smart.home.Room;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public class EventProcessor {
    public void executeEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        if (sensorEvent == null || smartHome == null) return;
        String deviceId = sensorEvent.getObjectId();
        SensorEventType eventType = sensorEvent.getType();

        Action action = createAction(eventType, deviceId);
        smartHome.execute(action);

        if (eventType == SensorEventType.DOOR_CLOSED && sensorEvent.getObjectId() != null) {
            Room deviceRoom = smartHome.getDeviceRoom(deviceId);
            if (deviceRoom != null && deviceRoom.getName().equals("hall"))
                smartHome.execute(new ActionLightOff(null));
        }
    }

    private Action createAction(SensorEventType type, String deviceId) {
        switch (type) {
            case DOOR_CLOSED:
                return new ActionDoorClose(deviceId);
            case DOOR_OPEN:
                return new ActionDoorOpen(deviceId);
            case LIGHT_OFF:
                return new ActionLightOff(deviceId);
            case LIGHT_ON:
                return new ActionLightOn(deviceId);
            case ALARM_ACTIVATE:
                return new ActionAlarmActivate(type.getCode());
            case ALARM_DEACTIVATE:
                return new ActionAlarmDeactivate(type.getCode());
            default:
                return null;
        }
    }
}
