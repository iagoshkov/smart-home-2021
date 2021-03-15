package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DoorEventsTest {

    private final SmartHome smartHome;
    private final SmartHomeEventHandler eventHandler;

    public DoorEventsTest() {
        SmartHomeCreator smartHomeCreator = new SomeSmartHomeCreator();
        smartHome = smartHomeCreator.create();

        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(),
                new DoorEventProcessor()
        );

        eventHandler = new SmartHomeEventHandler(smartHome, eventProcessors);
    }

    @Test
    public void applyOnExistingDoor() {
        AllDoorIdsAction allDoorIds = new AllDoorIdsAction();
        smartHome.execute(allDoorIds);

        for (String id : allDoorIds.getIds()) {
            checkIsDoorOpenByEvent(id, SensorEventType.DOOR_CLOSED, false);
            checkIsDoorOpenByEvent(id, SensorEventType.DOOR_OPEN, true);
        }
    }

    @Test
    public void applyOnNonExistingDoor() {
        AllDoorIdsAction allDoorIds = new AllDoorIdsAction();
        smartHome.execute(allDoorIds);

        String nonExistingId = "";
        do {
            nonExistingId += "@";
        } while (allDoorIds.getIds().contains(nonExistingId));

        checkIsDoorOpenByEvent(nonExistingId, SensorEventType.DOOR_CLOSED, null);
        checkIsDoorOpenByEvent(nonExistingId, SensorEventType.DOOR_OPEN, null);
    }

    private void checkIsDoorOpenByEvent(String id, SensorEventType doorEventType, Boolean isOpenExpected) {
        eventHandler.handleEvent(new SensorEvent(doorEventType, id));

        isDoorOpen(id, isOpenExpected);
    }

    private void isDoorOpen(String id, Boolean isOpenExpected) {
        IsDoorOpenAction isDoorOpen = new IsDoorOpenAction(id);
        smartHome.execute(isDoorOpen);
        Assert.assertEquals(isOpenExpected, isDoorOpen.isOpen());
    }
}