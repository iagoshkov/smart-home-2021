import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.sensorEvent.SensorEvent;
import ru.sbt.mipt.oop.sensorEvent.SensorLightOnEvent;

class SensorLightOnEventTest {

    @Test
    void handleEventLightOffTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "7";
        SensorEvent event = new SensorLightOnEvent(objectId);
        boolean isOn = smartHome.findLightByID(objectId).getObject().isActive();
        assertFalse(isOn);
        event.handleEvent(smartHome);
        isOn = smartHome.findLightByID(objectId).getObject().isActive();
        assertTrue(isOn);
    }

    @Test
    void handleEventLightOnTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "3";
        SensorEvent event = new SensorLightOnEvent(objectId);
        boolean isOn = smartHome.findLightByID(objectId).getObject().isActive();
        assertTrue(isOn);
        event.handleEvent(smartHome);
        isOn = smartHome.findLightByID(objectId).getObject().isActive();
        assertTrue(isOn);
    }
}