package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class LightEventsTest extends SmartHomeTestComponent {

    public LightEventsTest() {
        super();

        SmartHomeTest smartHomeTest = new SmartHomeTest();
        smartHome = smartHomeTest.smartHome;
        eventHandler = smartHomeTest.eventHandler;
    }

    @Test
    public void applyOnExistingLightTest() {
        AllLightsAction allLightsAction = new AllLightsAction();
        smartHome.execute(allLightsAction);

        List<Light> lights = allLightsAction.getLights();

        for (Light light : lights) {
            checkIsLightOnByEvent(light.getId(), SensorEventType.LIGHT_OFF, false);
            checkIsLightOnByEvent(light.getId(), SensorEventType.LIGHT_ON, true);
        }
    }

    @Test
    public void applyOnNonExistingLightTest() {
        AllLightsAction allLightsAction = new AllLightsAction();
        smartHome.execute(allLightsAction);

        List<String> lightIds = allLightsAction.getLights().stream().map(Light::getId).collect(Collectors.toList());

        String nonExistingId = "";
        do {
            nonExistingId += "@";
        } while (lightIds.contains(nonExistingId));

        checkIsLightOnByEvent(nonExistingId, SensorEventType.LIGHT_OFF, null);
        checkIsLightOnByEvent(nonExistingId, SensorEventType.LIGHT_ON, null);
    }

    private void checkIsLightOnByEvent(String id, SensorEventType lightEventType, Boolean isOpenExpected) {
        eventHandler.handleEvent(new SensorEvent(lightEventType, id));

        isLightOn(id, isOpenExpected);
    }

    private void isLightOn(String id, Boolean isOnExpected) {
        IsLightOnAction isLightOn = new IsLightOnAction(id);
        smartHome.execute(isLightOn);
        Assert.assertEquals(isOnExpected, isLightOn.isOn());
    }
}