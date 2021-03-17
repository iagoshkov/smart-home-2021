package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LightEventsTest {

    private final SmartHome smartHome;
    private final SmartHomeEventHandler eventHandler;

    public LightEventsTest() {
        SmartHomeCreator smartHomeCreator = new SomeSmartHomeCreator();
        smartHome = smartHomeCreator.create();

        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(),
                new DoorEventProcessor()
        );

        eventHandler = new SmartHomeEventHandler(smartHome, eventProcessors);
    }

    @Test
    public void applyOnExistingLight() {
        AllLightsAction allLightsAction = new AllLightsAction();
        smartHome.execute(allLightsAction);

        List<Light> lights = allLightsAction.getLights();

        for (Light light : lights) {
            checkIsLightOnByEvent(light.getId(), SensorEventType.LIGHT_OFF, false);
            checkIsLightOnByEvent(light.getId(), SensorEventType.LIGHT_ON, true);
        }
    }

    @Test
    public void applyOnNonExistingLight() {
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

    private void checkIsLightOnByEvent(String id, SensorEventType doorEventType, Boolean isOpenExpected) {
        eventHandler.handleEvent(new SensorEvent(doorEventType, id));

        IsLightOn(id, isOpenExpected);
    }

    private void IsLightOn(String id, Boolean isOnExpected) {
        IsLightOnAction isLightOn = new IsLightOnAction(id);
        smartHome.execute(isLightOn);
        Assert.assertEquals(isOnExpected, isLightOn.isOn());
    }
}