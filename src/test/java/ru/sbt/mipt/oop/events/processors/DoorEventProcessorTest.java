package ru.sbt.mipt.oop.events.processors;


import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.elements.alarm.AlarmState;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.DoorEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.DoorEventType;
import ru.sbt.mipt.oop.events.typedefs.HallDoorEventType;
import ru.sbt.mipt.oop.init.HomeLoader;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.Application.ACTIVATION_CODE;

public class DoorEventProcessorTest {
    private EventProcessor processor;
    private SmartHome smartHome;
    @Before
    public void initializeHome() {
        try {
            HomeLoader homeLoader = new JsonHomeLoader();
            smartHome = homeLoader.load(new FileInputStream("smart-home-1.js"));
            smartHome.addHomeComponent(HomeElementType.ALARM, new AlarmSystem(new StringId("ALARM"), ACTIVATION_CODE, AlarmState.DEACTIVATED));
            processor = new DoorEventProcessor();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void processDoorEvent_opensDoor() {
        ComponentId doorId = new StringId("2");
        Door testDoor = (Door) smartHome.getComponent((HomeComponent c) -> c.getType().equals(HomeElementType.DOOR) && c.getId().equals(doorId));
        assertFalse(testDoor.isOpen());
        Event event = new DoorEvent(DoorEventType.DOOR_OPEN, doorId);
        Event newEvent = processor.processEvent(smartHome, event);
        assertTrue(testDoor.isOpen());
        assertEquals(event, newEvent);
    }

    @Test
    public void processDoorEvent_closesDoor() {
        ComponentId doorId = new StringId("3");
        Door testDoor = (Door) smartHome.getComponent((HomeComponent c) -> c.getType().equals(HomeElementType.DOOR) && c.getId().equals(doorId));
        assertTrue(testDoor.isOpen());

        Event event = new DoorEvent(DoorEventType.DOOR_CLOSED, doorId);
        Event newEvent = processor.processEvent(smartHome, event);
        assertFalse(testDoor.isOpen());
        assertEquals(event, newEvent);
    }

    @Test
    public void processDoorEvent_closesHallDoor() {
        ComponentId doorId = new StringId("4");
        Door testDoor = (Door) smartHome.getComponent((HomeComponent c) -> c.getType().equals(HomeElementType.DOOR) && c.getId().equals(doorId));
        assertFalse(testDoor.isOpen());

        Event event = new DoorEvent(DoorEventType.DOOR_CLOSED, doorId);
        Event newEvent = processor.processEvent(smartHome, event);
        assertFalse(testDoor.isOpen());
        assertNotSame(event, newEvent);
        assertEquals(newEvent.getType(), HallDoorEventType.LIGHTS_OFF);

    }
}
