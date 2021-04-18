package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;

import java.util.List;
import java.util.stream.Collectors;

public class DoorEventsTest extends SmartHomeTestComponent {

    public DoorEventsTest() {
        super();

        SmartHomeTest smartHomeTest = new SmartHomeTest();
        set(smartHomeTest.getSmartHome(), smartHomeTest.getEventProcessor());
    }

    @Test
    public void applyOnExistingDoor() {
        for (Door door : doors) {
            checkIsDoorOpenByEvent(door.getId(), SensorEventType.DOOR_CLOSED, false);
            checkIsDoorOpenByEvent(door.getId(), SensorEventType.DOOR_OPEN, true);
        }
    }

    @Test
    public void applyOnNonExistingDoor() {
        List<String> doorIds = doors.stream()
                .map(Door::getId)
                .collect(Collectors.toList());

        String name = "";
        do {
            name += "@";
        } while (doorIds.contains(name));

        String nonExistingId = name;

        checkIsDoorOpenByEvent(nonExistingId, SensorEventType.DOOR_CLOSED, null);
        checkIsDoorOpenByEvent(nonExistingId, SensorEventType.DOOR_OPEN, null);
    }

    private void checkIsDoorOpenByEvent(String id, SensorEventType doorEventType, Boolean isOpenExpected) {
        eventProcessor.processEvent(new SensorEvent(doorEventType, id));

        Door door = getDoor(id);
        if (door != null) {
            Assert.assertEquals(isOpenExpected, door.isOpen());
        }
    }

}
