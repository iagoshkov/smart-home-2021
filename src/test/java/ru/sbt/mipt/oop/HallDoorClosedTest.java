package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.actions.AllDoorsAction;
import ru.sbt.mipt.oop.actions.AllLightsAction;
import ru.sbt.mipt.oop.actions.AllRoomsAction;

import java.util.ArrayList;
import java.util.List;

public class HallDoorClosedTest extends SmartHomeTestComponent {

    public HallDoorClosedTest() {
        super();

        SmartHomeTest smartHomeTest = new SmartHomeTest();
        smartHome = smartHomeTest.smartHome;
        eventHandler = smartHomeTest.eventHandler;
    }

    private void isLightTurnedOff(String id) {
        IsLightOnAction isLightOn = new IsLightOnAction(id);
        smartHome.execute(isLightOn);
        Assert.assertEquals(Boolean.FALSE, isLightOn.isOn());
    }

    @Test
    public void closeHallDoor() {
        AllDoorsAction allDoorsAction = new AllDoorsAction();
        smartHome.execute(allDoorsAction);

        List<Door> doors = allDoorsAction.getDoors();
        if (doors.isEmpty()) return;

        AllLightsAction allLightsAction = new AllLightsAction();
        smartHome.execute(allLightsAction);

        List<Light> lights = allLightsAction.getLights();
        if (lights.isEmpty()) return;

        // At least one door is open
        eventHandler.handleEvent(new SensorEvent(SensorEventType.DOOR_OPEN, doors.get(0).getId()));

        // At least one light is on
        eventHandler.handleEvent(new SensorEvent(SensorEventType.LIGHT_ON, lights.get(0).getId()));

        Door hallRoomDoor = findHallRoomDoor();

        if (hallRoomDoor == null) return;

        String hallRoomDoorId = hallRoomDoor.getId();

        // Close hall door, so all lights will be turned off
        eventHandler.handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, hallRoomDoorId));

        for (Light light : lights) {
            isLightTurnedOff(light.getId());
        }
    }

    private Door findHallRoomDoor() {
        String hallRoomName = "hall";

        AllRoomsAction allRoomsAction = new AllRoomsAction();
        smartHome.execute(allRoomsAction);

        for (Room room : allRoomsAction.getRooms()) {
            if (room.getName().equals(hallRoomName)) {
                AllDoorsAction allDoorsAction = new AllDoorsAction();
                room.execute(allDoorsAction);

                List<Door> doors = allDoorsAction.getDoors();
                if (!doors.isEmpty()) {
                    return doors.iterator().next();
                }
            }
        }
        return null;
    }

    @Test
    public void closeNotHallDoor() {
        AllLightsAction allLightsAction = new AllLightsAction();
        smartHome.execute(allLightsAction);

        List<Light> lights = allLightsAction.getLights();
        if (lights.isEmpty()) return;

        // At least one light is on
        eventHandler.handleEvent(new SensorEvent(SensorEventType.LIGHT_ON, lights.get(0).getId()));

        Door notHallRoomDoor = findNotHallRoomDoor();

        if (notHallRoomDoor == null) return;

        String notHallRoomDoorId = notHallRoomDoor.getId();

        List<Boolean> lightIsOnListBefore = getLightIsOnList(lights);

        // Close not hall door, so it doesn't make anything
        eventHandler.handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, notHallRoomDoorId));

        List<Boolean> lightIsOnListAfter = getLightIsOnList(lights);

        Assert.assertEquals(lightIsOnListBefore, lightIsOnListAfter);
    }

    private List<Boolean> getLightIsOnList(List<Light> lights) {
        List<Boolean> lightIsOnList = new ArrayList<>();

        for (Light light : lights) {
            IsLightOnAction isLightOn = new IsLightOnAction(light.getId());
            smartHome.execute(isLightOn);
            lightIsOnList.add(isLightOn.isOn());
        }
        return lightIsOnList;
    }

    private Door findNotHallRoomDoor() {
        String hallRoomName = "hall";

        AllRoomsAction allRoomsAction = new AllRoomsAction();
        smartHome.execute(allRoomsAction);

        for (Room room : allRoomsAction.getRooms()) {
            if (!room.getName().equals(hallRoomName)) {
                AllDoorsAction allDoorsAction = new AllDoorsAction();
                room.execute(allDoorsAction);

                List<Door> doors = allDoorsAction.getDoors();
                if (!doors.isEmpty()) {
                    return doors.iterator().next();
                }
            }
        }
        return null;
    }

}
