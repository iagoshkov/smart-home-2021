package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.light.Light;

import static org.junit.jupiter.api.Assertions.*;


class LightEventHandlerTest {

    @org.junit.jupiter.api.Test
    void handleLightEventLightOnChangesLightStatusToFromOnToOff() {
        String tmpId = "123";
        Light light = new Light(tmpId, false);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "123");
        LightEventHandler lightEventHandler = new LightEventHandler(event, light);
        lightEventHandler.handleEvent();
        assertTrue(light.isOn());
        lightEventHandler.handleEvent();
        assertTrue(light.isOn());
    }

    @org.junit.jupiter.api.Test
    void handleLightEventLightOffChangesLightStatusToFromOffToOn() {
        String tmpId = "123";
        Light light = new Light(tmpId, false);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "123");
        LightEventHandler lightEventHandler = new LightEventHandler(event, light);
        lightEventHandler.handleEvent();
        assertFalse(light.isOn());
        lightEventHandler.handleEvent();
        assertFalse(light.isOn());
    }

}