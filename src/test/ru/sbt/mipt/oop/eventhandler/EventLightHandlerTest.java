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
        EventHandler handler = new EventLightHandler(smartHome);
        assertFalse(smartHome.findLightByID(objectId).isOn());
        handler.handleEvent(event);
        assertTrue(smartHome.findLightByID(objectId).isOn());
    }

    @Test
    void handleEventLightOffTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "2";
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, objectId);
        EventHandler handler = new EventLightHandler(smartHome);
        assertTrue(smartHome.findLightByID(objectId).isOn());
        handler.handleEvent(event);
        assertFalse(smartHome.findLightByID(objectId).isOn());
    }
}