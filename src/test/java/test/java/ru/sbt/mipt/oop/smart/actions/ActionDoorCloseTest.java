package test.java.ru.sbt.mipt.oop.smart.actions;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smart.actions.ActionDoorClose;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.home.locations.Room;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ActionDoorCloseTest {
    @Test
    void act_whenDoorIsClose() {
        // given
        Door door = new Door("1", false, new Room("Bathroom"));
        // when
        new ActionDoorClose().act(door);
        boolean isOpen = door.isOpen();
        // then
        assertFalse(isOpen);
    }

    @Test
    void act_whenDoorIsOpen() {
        // given
        Door door = new Door("1", true, new Room("Bathroom"));
        // when
        new ActionDoorClose().act(door);
        boolean isOpen = door.isOpen();
        // then
        assertFalse(isOpen);
    }
}