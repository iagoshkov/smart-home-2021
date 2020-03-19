package ru.sbt.mipt.oop.eventhandler;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class EventLightHandlerTest {

    private Light findLightByID(SmartHome smartHome, String id) {
        AtomicReference<Light> light = new AtomicReference<>();
        smartHome.execute(lightCandidate -> {
            if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals(id)) {
                light.set((Light) lightCandidate);
            }
        });
        return light.get();
    }

    @Test
    void handleEventLightOnTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "1";
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, objectId);
        EventLightHandler handler = new EventLightHandler(smartHome);
        assertFalse(findLightByID(smartHome, objectId).isOn());
        handler.handleEvent(event);
        assertTrue(findLightByID(smartHome, objectId).isOn());
    }

    @Test
    void handleEventLightOffTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "2";
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, objectId);
        EventLightHandler handler = new EventLightHandler(smartHome);
        assertTrue(findLightByID(smartHome, objectId).isOn());
        handler.handleEvent(event);
        assertFalse(findLightByID(smartHome, objectId).isOn());
    }
}