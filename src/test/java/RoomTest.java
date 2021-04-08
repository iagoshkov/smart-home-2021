import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void testExecute() {

        Light light = new Light("kitchenlight", false);
        Door door = new Door(false,"kitchendoor");
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "kitchen");
        room.execute(component -> {

            if (component instanceof Light) {

                Light currentLight = (Light) component;
                currentLight.setOn(true);

            } else {

                if (component instanceof Door) {

                    Door currentDoor = (Door) component;
                    currentDoor.setOpen(true);
                }
            }
        });
        assertTrue(light.isOn());
        assertTrue(door.isOpen());
    }
}
