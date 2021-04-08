import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorEventProcessorTest {

    private final Door livingRoomDoor = new Door(true, "livingroom");
    private final Door kitchenDoor = new Door(false, "kitchen");
    private final Door hallDoor = new Door(true, "hall");
    private final Light livingRoomLight = new Light("livingroom", true);
    private final Light kitchenLight = new Light("kitchen", false);
    private final Light hallLight = new Light("hall", true);

    private SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(livingRoomLight), Arrays.asList(livingRoomDoor), "livingroom"),
            new Room(Arrays.asList(kitchenLight), Arrays.asList(kitchenDoor), "kitchen"),
            new Room(Arrays.asList(hallLight), Arrays.asList(hallDoor), "hall")
    ));

    private final EventProcessor processor = new HallDoorEventProcessor();


    @Test
    public void testHallDoorOpens() {
        processor.processEvent(smartHome, new SensorEvent(DOOR_OPEN, "hall"));

        assertTrue(hallLight.isOn());
        assertTrue(hallDoor.isOpen());
    }

    @Test
    public void testHallDoorCloseEvent() {
        processor.processEvent(smartHome, new SensorEvent(DOOR_CLOSED, "hall"));

        assertFalse(livingRoomLight.isOn());
        assertFalse(kitchenLight.isOn());
        assertFalse(hallLight.isOn());
        assertFalse(hallDoor.isOpen());
    }
}
