import org.junit.Test;
import ru.sbt.mipt.oop.DoorEventProcessor;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessorTest {

    private final Door livingRoomDoor = new Door(true, "livingroom");
    private final Door kitchenDoor = new Door(false, "kitchen");
    private final Light livingRoomLight = new Light("livingroom", true);
    private final Light kitchenLight = new Light("kitchen", false);

    private final SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(livingRoomLight), Arrays.asList(livingRoomDoor), "livingroom"),
            new Room(Arrays.asList(kitchenLight), Arrays.asList(kitchenDoor), "kitchen")
    ));

    private final EventProcessor processor = new DoorEventProcessor();

    @Test
    public void testOpenedDoorCloses() {
        processor.processEvent(smartHome, new SensorEvent(DOOR_CLOSED, "kitchen"));
        assertFalse(kitchenDoor.isOpen());
    }

    @Test
    public void testClosedDoorOpens() {
        processor.processEvent(smartHome, new SensorEvent(DOOR_OPEN, "kitchen"));
        assertTrue(kitchenDoor.isOpen());
    }

}
