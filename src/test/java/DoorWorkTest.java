import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.handlers.DoorSensorEventHandler;
import ru.sbt.mipt.oop.handlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.handlers.SensorEventHandler;
import ru.sbt.mipt.oop.readers.JSONSmartHomeReader;
import ru.sbt.mipt.oop.readers.SmartHomeReader;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoorWorkTest {
    private static SmartHome smartHome;

    @BeforeAll
    static void init() {
        String filename = "./src/test/resources/smart-home-test.js";
        SmartHomeReader reader = new JSONSmartHomeReader(filename);
        smartHome = reader.readSmartHome();
    }

    @Test
    public void lightOffWork() {
        Door door = getDoor("1");
        assertFalse(door.isOpen());
    }

    @Test
    public void lightOnWork() {
        Door door = getDoor("3");
        assertTrue(door.isOpen());
    }

    @Test
    public void lightSwitchWork() {
        SensorEvent doorSwitch = new SensorEvent(SensorEventType.DOOR_CLOSED,"3");
        SensorEventHandler eventHandler = new DoorSensorEventHandler(smartHome, doorSwitch);
        eventHandler.handleEvent();

        Door door = getDoor("3");
        assertFalse(door.isOpen());
    }

    private Door getDoor(String doorId) {
        Door doorResult = null;
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    doorResult = door;
                }
            }
        }
        return doorResult;
    }
}
