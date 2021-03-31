package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class DoorEventsTest extends SmartHomeTestComponent {

    public DoorEventsTest() {
        super();

        SmartHomeTest smartHomeTest = new SmartHomeTest();
        smartHome = smartHomeTest.smartHome;
        eventHandler = smartHomeTest.eventHandler;
    }

    @Test
    public void applyOnExistingDoor() {
        AllDoorsAction allDoorsAction = new AllDoorsAction();
        smartHome.execute(allDoorsAction);

        List<Door> doors = allDoorsAction.getDoors();

        for (Door door : doors) {
            checkIsDoorOpenByEvent(door.getId(), SensorEventType.DOOR_CLOSED, false);
            checkIsDoorOpenByEvent(door.getId(), SensorEventType.DOOR_OPEN, true);
        }
    }

    @Test
    public void applyOnNonExistingDoor() {
        AllDoorsAction allDoorsAction = new AllDoorsAction();
        smartHome.execute(allDoorsAction);

        List<String> doorIds = allDoorsAction.getDoors().stream().map(Door::getId).collect(Collectors.toList());

        String nonExistingId = "";
        do {
            nonExistingId += "@";
        } while (doorIds.contains(nonExistingId));

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
