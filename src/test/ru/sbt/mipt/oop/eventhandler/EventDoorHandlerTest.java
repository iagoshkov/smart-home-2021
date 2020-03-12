package ru.sbt.mipt.oop.eventhandler;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.SmartHome;

import static org.junit.jupiter.api.Assertions.*;

class EventDoorHandlerTest {

    @Test
    void handleEventOpenDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "4";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, objectId);
        EventDoorHandler handler = new EventDoorHandler(smartHome);
        assertFalse(handler.findDoorByID(objectId).isOpen());
        handler.handleEvent(event);
        assertTrue(handler.findDoorByID(objectId).isOpen());
    }

    @Test
    void handleEventCloseDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "3";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, objectId);
        EventDoorHandler handler = new EventDoorHandler(smartHome);
        assertTrue(handler.findDoorByID(objectId).isOpen());
        handler.handleEvent(event);
        assertFalse(handler.findDoorByID(objectId).isOpen());
    }

}