import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.sensorEvent.SensorDoorClosedEvent;
import ru.sbt.mipt.oop.sensorEvent.SensorEvent;

import static org.junit.jupiter.api.Assertions.*;

class SensorDoorClosedEventTest {

    @Test
    void handleEventOpenDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "3";
        SensorEvent event = new SensorDoorClosedEvent(objectId);
        assertTrue(smartHome.findDoorByID(objectId).getObject().isOpen());
        event.handleEvent(smartHome);
        assertFalse(smartHome.findDoorByID(objectId).getObject().isOpen());
    }

    @Test
    void handleEventClosedDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "1";
        SensorEvent event = new SensorDoorClosedEvent(objectId);
        assertFalse(smartHome.findDoorByID(objectId).getObject().isOpen());
        event.handleEvent(smartHome);
        assertFalse(smartHome.findDoorByID(objectId).getObject().isOpen());
    }
}