package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        AllLightIdsAction allLightIds = new AllLightIdsAction();
        smartHome.execute(allLightIds);

        for (String id : allLightIds.getIds()) {
            checkIsLightOnByEvent(id, SensorEventType.LIGHT_OFF, false);
            checkIsLightOnByEvent(id, SensorEventType.LIGHT_ON, true);
        }
    }

    @Test
    public void applyOnNonExistingLight() {
        AllLightIdsAction allLightIds = new AllLightIdsAction();
        smartHome.execute(allLightIds);

        String nonExistingId = "";
        do {
            nonExistingId += "@";
        } while (allLightIds.getIds().contains(nonExistingId));

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