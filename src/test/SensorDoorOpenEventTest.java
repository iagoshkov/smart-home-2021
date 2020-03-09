import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.sensorEvent.SensorDoorOpenEvent;
import ru.sbt.mipt.oop.sensorEvent.SensorEvent;

import static org.junit.jupiter.api.Assertions.*;

class SensorDoorOpenEventTest {

    @Test
    void handleEventOpenDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "3";
        SensorEvent event = new SensorDoorOpenEvent(objectId);
        assertTrue(smartHome.findDoorByID(objectId).getObject().isActive());
        event.handleEvent(smartHome);
        assertTrue(smartHome.findDoorByID(objectId).getObject().isActive());
    }

    @Test
    void handleEventClosedDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "2";
        SensorEvent event = new SensorDoorOpenEvent(objectId);
        assertFalse(smartHome.findDoorByID(objectId).getObject().isActive());
        event.handleEvent(smartHome);
        assertTrue(smartHome.findDoorByID(objectId).getObject().isActive());
    }
}