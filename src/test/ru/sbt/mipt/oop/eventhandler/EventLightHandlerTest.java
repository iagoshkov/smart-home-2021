package ru.sbt.mipt.oop.eventhandler;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.SmartHome;

import static org.junit.jupiter.api.Assertions.*;

class EventLightHandlerTest {

    @Test
    void handleEventLightOnTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "1";
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, objectId);
        EventLightHandler handler = new EventLightHandler(smartHome);
        assertFalse(handler.findLightByID(objectId).isOn());
        handler.handleEvent(event);
        assertTrue(handler.findLightByID(objectId).isOn());
    }

    @Test
    void handleEventLightOffTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "2";
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, objectId);
        EventLightHandler handler = new EventLightHandler(smartHome);
        assertTrue(handler.findLightByID(objectId).isOn());
        handler.handleEvent(event);
        assertFalse(handler.findLightByID(objectId).isOn());
    }
}