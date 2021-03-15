package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HallDoorClosedTest {

    private final SmartHome smartHome;
    private final SmartHomeEventHandler eventHandler;

    public HallDoorClosedTest() {
        SmartHomeCreator smartHomeCreator = new SomeSmartHomeCreator();
        smartHome = smartHomeCreator.create();

        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(),
                new DoorEventProcessor()
        );

        eventHandler = new SmartHomeEventHandler(smartHome, eventProcessors);
    }

    private void isDoorOpen(String id, Boolean isOpenExpected) {
        IsDoorOpenAction isDoorOpen = new IsDoorOpenAction(id);
        smartHome.execute(isDoorOpen);
        Assert.assertEquals(isOpenExpected, isDoorOpen.isOpen());
    }

    private void IsLightOn(String id, Boolean isOnExpected) {
        IsLightOnAction isLightOn = new IsLightOnAction(id);
        smartHome.execute(isLightOn);
        Assert.assertEquals(isOnExpected, isLightOn.isOn());
    }

    @Test
    public void closeHallDoor() {
        AllDoorIdsAction allDoorIds = new AllDoorIdsAction();
        smartHome.execute(allDoorIds);
        if (allDoorIds.getIds().isEmpty()) return;

        AllLightIdsAction allLightIds = new AllLightIdsAction();
        smartHome.execute(allLightIds);
        if (allLightIds.getIds().isEmpty()) return;

        // At least one door is open
        eventHandler.handleEvent(new SensorEvent(SensorEventType.DOOR_OPEN, allDoorIds.getIds().get(0)));

        // At least one light is on
        eventHandler.handleEvent(new SensorEvent(SensorEventType.LIGHT_ON, allLightIds.getIds().get(0)));

        Door hallRoomDoor = findHallRoomDoor();

        if (hallRoomDoor == null) return;

        String hallRoomDoorId = hallRoomDoor.getId();

        // Close hall door, so all lights will be turned off
        eventHandler.handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, hallRoomDoorId));

        for (String id : allLightIds.getIds()) {
            IsLightOn(id, Boolean.FALSE);
        }
    }

    private Door findHallRoomDoor() {
        String hallRoomName = "hall";

        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals(hallRoomName) && !room.getDoors().isEmpty()) {
                return room.getDoors().iterator().next();
            }
        }
        return null;
    }

    @Test
    public void closeNotHallDoor() {
        AllLightIdsAction allLightIds = new AllLightIdsAction();
        smartHome.execute(allLightIds);
        if (allLightIds.getIds().isEmpty()) return;

        // At least one light is on
        eventHandler.handleEvent(new SensorEvent(SensorEventType.LIGHT_ON, allLightIds.getIds().get(0)));

        Door notHallRoomDoor = findNotHallRoomDoor();

        if (notHallRoomDoor == null) return;

        String notHallRoomDoorId = notHallRoomDoor.getId();

        List<Boolean> lightIsOnListBefore = getLightIsOnList(allLightIds);

        // Close not hall door, so it doesn't make anything
        eventHandler.handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, notHallRoomDoorId));

        List<Boolean> lightIsOnListAfter = getLightIsOnList(allLightIds);

        Assert.assertEquals(lightIsOnListBefore, lightIsOnListAfter);
    }

    private List<Boolean> getLightIsOnList(AllLightIdsAction allLightIds) {
        List<Boolean> lightIsOnList = new ArrayList<>();

        for (String id : allLightIds.getIds()) {
            IsLightOnAction isLightOn = new IsLightOnAction(id);
            smartHome.execute(isLightOn);
            lightIsOnList.add(isLightOn.isOn());
        }
        return lightIsOnList;
    }

    private Door findNotHallRoomDoor() {
        String hallRoomName = "hall";

        for (Room room : smartHome.getRooms()) {
            if (!room.getName().equals(hallRoomName) && !room.getDoors().isEmpty()) {
                return room.getDoors().iterator().next();
            }
        }
        return null;
    }
}