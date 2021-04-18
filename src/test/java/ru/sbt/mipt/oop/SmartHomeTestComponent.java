package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.actions.AllDoorsAction;
import ru.sbt.mipt.oop.actions.AllLightsAction;
import ru.sbt.mipt.oop.actions.AllRoomsAction;
import ru.sbt.mipt.oop.event.processor.EventProcessor;

import java.util.List;
import java.util.stream.Collectors;

public class SmartHomeTestComponent {

    protected SmartHome smartHome = null;
    protected EventProcessor eventProcessor = null;

    protected List<Door> doors = null;
    protected List<Light> lights = null;
    protected List<Room> rooms = null;

    protected final String hallRoomName = "hall";

    public EventProcessor getEventProcessor() {
        return eventProcessor;
    }

    public SmartHome getSmartHome() {
        return smartHome;
    }

    public void set(SmartHome smartHome, EventProcessor eventProcessor) {
        this.smartHome = smartHome;
        this.eventProcessor = eventProcessor;

        findAll();
    }

    public void findAll() {
        rooms = getRooms(smartHome);
        doors = getDoors(smartHome);
        lights = getLights(smartHome);
    }

    protected List<Room> getRooms(Actionable actionable) {
        AllRoomsAction allRoomsAction = new AllRoomsAction();
        actionable.execute(allRoomsAction);

        return allRoomsAction.getRooms();
    }

    protected List<Door> getDoors(Actionable actionable) {
        AllDoorsAction allDoorsAction = new AllDoorsAction();
        actionable.execute(allDoorsAction);

        return allDoorsAction.getDoors();
    }

    protected List<Light> getLights(Actionable actionable) {
        AllLightsAction allLightsAction = new AllLightsAction();
        actionable.execute(allLightsAction);

        return allLightsAction.getLights();
    }

    protected Room getRoom(String name) {
        return rooms.stream().filter(room -> room.getName().equals(name)).findAny().orElse(null);
    }

    protected Door getDoor(String id) {
        return doors.stream().filter(door -> door.getId().equals(id)).findAny().orElse(null);
    }

    protected Light getLight(String id) {
        return lights.stream().filter(light -> light.getId().equals(id)).findAny().orElse(null);
    }

    protected List<Boolean> getLightIsOnList(List<Light> lights) {
        return lights.stream()
                .map(Light::isOn)
                .collect(Collectors.toList());
    }

    protected List<Boolean> getDoorIsOpenList(List<Door> doors) {
        return doors.stream()
                .map(Door::isOpen)
                .collect(Collectors.toList());
    }

}
