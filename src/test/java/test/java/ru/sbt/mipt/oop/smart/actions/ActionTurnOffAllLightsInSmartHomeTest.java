package test.java.ru.sbt.mipt.oop.smart.actions;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smart.actions.ActionTurnOffAllLightsInSmartHome;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceType;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.locations.Room;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ActionTurnOffAllLightsInSmartHomeTest {
    @Test
    void act_whenAllLightIsOn() {
        // given
        SmartHome smartHome = new SmartHome("0", "Smart Home");
        Room room = new Room("Simple room");
        smartHome.addDevice(new Light("1", true, room));
        smartHome.addDevice(new Light("2", true, room));
        smartHome.addDevice(new Light("3", true, room));
        smartHome.addDevice(new Light("4", true, room));
        smartHome.addDevice(new Door("5", true, room));
        smartHome.addDevice(new Door("6", true, room));
        // when
        new ActionTurnOffAllLightsInSmartHome().act(smartHome);
        var lightArray = smartHome
                                    .getAllDevices()
                                    .stream()
                                    .filter(d -> d.getType() == SmartDeviceType.LIGHT)
                                    .toArray();
        // then
        for (var device : lightArray) {
            Light light = (Light) device;
            assertFalse(light.isOn());
        }
    }

    @Test
    void act_whenAllLightIsOff() {
        // given
        SmartHome smartHome = new SmartHome("0", "Smart Home");
        Room room = new Room("Simple room");
        smartHome.addDevice(new Light("1", false, room));
        smartHome.addDevice(new Light("2", false, room));
        smartHome.addDevice(new Light("3", false, room));
        smartHome.addDevice(new Light("4", false, room));
        smartHome.addDevice(new Door("5", false, room));
        smartHome.addDevice(new Door("6", false, room));
        // when
        new ActionTurnOffAllLightsInSmartHome().act(smartHome);
        var lightArray = smartHome
                .getAllDevices()
                .stream()
                .filter(d -> d.getType() == SmartDeviceType.LIGHT)
                .toArray();
        // then
        for (var device : lightArray) {
            Light light = (Light) device;
            assertFalse(light.isOn());
        }
    }
}