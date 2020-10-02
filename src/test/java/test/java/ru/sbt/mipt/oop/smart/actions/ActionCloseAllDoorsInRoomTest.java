package test.java.ru.sbt.mipt.oop.smart.actions;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smart.actions.ActionCloseAllDoorsInRoom;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceType;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.locations.Room;

import static org.junit.jupiter.api.Assertions.*;

class ActionCloseAllDoorsInRoomTest {
    @Test
    void act_whenAllDoorInRoomIsOpen() {
        // given
        SmartHome smartHome = new SmartHome("0", "Smart Home");
        Room room = new Room("Simple room");
        smartHome.addDevice(new Light("1", true, room));
        smartHome.addDevice(new Light("2", true, room));
        smartHome.addDevice(new Light("3", true, room));
        smartHome.addDevice(new Light("4", false, room));
        smartHome.addDevice(new Door("5", true, room));
        smartHome.addDevice(new Door("6", true, room));
        // when
        new ActionCloseAllDoorsInRoom(room).act(smartHome);
        var doorsArray = smartHome
                .getAllDevices()
                .stream()
                .filter(d -> d.getType() == SmartDeviceType.DOOR)
                .toArray();
        // then
        for (var device : doorsArray) {
            Door door = (Door) device;
            assertFalse(door.isOpen());
        }
    }

    @Test
    void act_whenAllDoorInRoomIsClose() {
        // given
        SmartHome smartHome = new SmartHome("0", "Smart Home");
        Room room = new Room("Simple room");
        smartHome.addDevice(new Light("1", true, room));
        smartHome.addDevice(new Light("2", true, room));
        smartHome.addDevice(new Light("3", true, room));
        smartHome.addDevice(new Light("4", false, room));
        smartHome.addDevice(new Door("5", false, room));
        smartHome.addDevice(new Door("6", false, room));
        // when
        new ActionCloseAllDoorsInRoom(room).act(smartHome);
        var doorsArray = smartHome
                .getAllDevices()
                .stream()
                .filter(d -> d.getType() == SmartDeviceType.DOOR)
                .toArray();
        // then
        for (var device : doorsArray) {
            Door door = (Door) device;
            assertFalse(door.isOpen());
        }
    }
}