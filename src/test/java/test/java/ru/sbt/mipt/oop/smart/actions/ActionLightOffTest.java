package test.java.ru.sbt.mipt.oop.smart.actions;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smart.actions.ActionDoorOpen;
import ru.sbt.mipt.oop.smart.actions.ActionLightOff;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.home.locations.Room;

import static org.junit.jupiter.api.Assertions.*;

class ActionLightOffTest {
    @Test
    void act_whenLightIsOff() {
        // given
        Light light = new Light("1", false, new Room("Bathroom"));
        // when
        new ActionLightOff().act(light);
        boolean isOn = light.isOn();
        // then
        assertFalse(isOn);
    }

    @Test
    void act_whenLightIsOn() {
        // given
        Light light = new Light("1", true, new Room("Bathroom"));
        // when
        new ActionLightOff().act(light);
        boolean isOn = light.isOn();
        // then
        assertFalse(isOn);
    }
}