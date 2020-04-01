package ru.sbt.mipt.oop.smarthome.remotecontrol;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoteControllerTest {
    private int value = 0;
    private RemoteController rc = new RemoteController();

    @Before
    public void setUp() {
        rc.setCommand("A", () -> {value = 1;});
        rc.setCommand("1", () -> {value = 2;});
    }

    @Test
    public void testButtonPressed() {
        rc.onButtonPressed("A", "rc_id");
        assertEquals(1, value);
        rc.onButtonPressed("1", "rc_id");
        assertEquals(2, value);
    }
}