package ru.sbt.mipt.oop.eventhandler;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class EventDoorHandlerTest {

    @Test
    void handleEventOpenDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "4";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, objectId);
        EventHandler handler = new EventDoorHandler(smartHome);
        assertFalse(smartHome.findDoorByID(objectId).isOpen());
        handler.handleEvent(event);
        assertTrue(smartHome.findDoorByID(objectId).isOpen());
    }

    @Test
    void handleEventCloseDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "3";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, objectId);
        EventHandler handler = new EventDoorHandler(smartHome);
        assertTrue(smartHome.findDoorByID(objectId).isOpen());
        handler.handleEvent(event);
        assertFalse(smartHome.findDoorByID(objectId).isOpen());
    }

    @Test
    void handleEventCloseHallDoorTest() {
        Room hall = new Room("hall");
        hall.addLights(Arrays.asList(new Light("7", true), new Light("8", true), new Light("9", true)));
        hall.addDoors(Collections.singletonList(new Door(true, "4")));
        SmartHome smartHome = new SmartHome(Collections.singletonList(hall));
        String objectId = "4";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, objectId);
        EventHandler handler = new EventDoorHandler(smartHome);
        assertTrue(smartHome.findDoorByID(objectId).isOpen());
        handler.handleEvent(event);
        assertFalse(smartHome.findDoorByID(objectId).isOpen());
        smartHome.execute(light -> {
            if (light instanceof Light) {
                assertFalse(((Light)light).isOn());
            }
        });
    }

}