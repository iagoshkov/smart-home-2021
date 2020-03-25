package ru.sbt.mipt.oop.smarthome.events;

import com.coolcompany.smarthome.events.CCSensorEvent;

import java.util.HashMap;
import java.util.Map;

public class CCEventAdapter {
    private Map<String, EventType> getTypeByCCString = new HashMap<>();

    CCEventAdapter() {
        String[] eventTypes = new String[] {
                "LightIsOn", "LightIsOff",
                "DoorIsOpen", "DoorIsClosed", "DoorIsLocked", "DoorIsUnlocked" };
        for (int i = 0; i < eventTypes.length; i++) {
            getTypeByCCString.put(eventTypes[i], EventType.values()[i]);
        }
    }

    SensorEvent adapt(CCSensorEvent ccEvent) {
        return new SensorEvent(getTypeByCCString.get(ccEvent.getEventType()), ccEvent.getObjectId());
    }
}
