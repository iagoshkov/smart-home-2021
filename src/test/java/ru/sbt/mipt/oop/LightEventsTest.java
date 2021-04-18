package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;

import java.util.List;
import java.util.stream.Collectors;

public class LightEventsTest extends SmartHomeTestComponent {

    public LightEventsTest() {
        super();

        SmartHomeTest smartHomeTest = new SmartHomeTest();
        set(smartHomeTest.getSmartHome(), smartHomeTest.getEventProcessor());
    }

    @Test
    public void applyOnExistingLight() {
        for (Light light : lights) {
            checkIsLightOnByEvent(light.getId(), SensorEventType.LIGHT_OFF, false);
            checkIsLightOnByEvent(light.getId(), SensorEventType.LIGHT_ON, true);
        }
    }

    @Test
    public void applyOnNonExistingLight() {
        List<String> lightIds = lights.stream()
                .map(Light::getId)
                .collect(Collectors.toList());

        String name = "";
        do {
            name += "@";
        } while (lightIds.contains(name));

        String nonExistingId = name;

        checkIsLightOnByEvent(nonExistingId, SensorEventType.LIGHT_OFF, null);
        checkIsLightOnByEvent(nonExistingId, SensorEventType.LIGHT_ON, null);
    }

    private void checkIsLightOnByEvent(String id, SensorEventType lightEventType, Boolean isOpenExpected) {
        eventProcessor.processEvent(new SensorEvent(lightEventType, id));

        Light light = getLight(id);
        if (light != null) {
            Assert.assertEquals(isOpenExpected, light.isOn());
        }
    }

}
