package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class LightSwitcherTest {
    Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)), Arrays.asList(),
            "kitchen");
    Room bedroom = new Room(Arrays.asList(new Light("4", true), new Light("5", false),
            new Light("6", true)),
            Arrays.asList(), "bedroom");
    SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bedroom));
    LightSwitcher lightSwitcher = new LightSwitcher(smartHome);

    private Light getLightById(SmartHome smartHome, String id) {
        for (Room room: smartHome.getRooms()) {
            for (Light light: room.getLights()) {
                if (light.getId().equals(id)) {
                    return light;
                }
            }
        }
        return null;
    }

    @Test
    public void onOffedLight() {
        lightSwitcher.turnOn("1");

        assertEquals(true, getLightById(smartHome, "1").isOn());
    }

    @Test
    public void onOnnedLight() {
        lightSwitcher.turnOn("2");

        assertEquals(true, getLightById(smartHome,"2").isOn());
    }

    @Test
    public void offOnnedLight() {
        lightSwitcher.turnOff("2");

        assertEquals(false, getLightById(smartHome,"2").isOn());
    }

    @Test
    public void offAll() {
        lightSwitcher.turnOffAll();

        for (Room room: smartHome.getRooms()) {
            for (Light light: room.getLights()) {
                assertEquals(false, light.isOn());
            }
        }

    }

}