package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


class LightEventHandlerTest {

    @org.junit.jupiter.api.Test
    void handleLightEventLightOnChangesLightStatusToFromOnToOff() {
        String tmpId = "123";
        Light light = new Light(tmpId, false);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "123");
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Light> lights = new ArrayList<>();
        lights.add(light);
        Room room1 = new Room(lights, null, "name");
        rooms.add(room1);
        SmartHome smartHome = new SmartHome(rooms);
        LightEventHandler lightEventHandler = new LightEventHandler();
        lightEventHandler.handleEvent(event, smartHome);
        assertTrue(light.isOn());
        lightEventHandler.handleEvent(event, smartHome);
        assertTrue(light.isOn());
    }

    @org.junit.jupiter.api.Test
    void handleLightEventLightOffChangesLightStatusToFromOffToOn() {
        String tmpId = "123";
        Light light = new Light(tmpId, false);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "123");
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Light> lights = new ArrayList<>();
        lights.add(light);
        Room room1 = new Room(lights, null, "name");
        rooms.add(room1);
        SmartHome smartHome = new SmartHome(rooms);
        LightEventHandler lightEventHandler = new LightEventHandler();
        lightEventHandler.handleEvent(event, smartHome);
        assertFalse(light.isOn());
        lightEventHandler.handleEvent(event, smartHome);
        assertFalse(light.isOn());
    }

}