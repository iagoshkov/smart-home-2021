import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.sensorEvent.SensorEvent;
import ru.sbt.mipt.oop.sensorEvent.SensorLightOffEvent;
import ru.sbt.mipt.oop.sensorEvent.SensorLightOnEvent;

import static org.junit.jupiter.api.Assertions.*;


class SensorLightOffEventTest {

    @Test
    void handleEventLightOnTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "2";
        SensorEvent event = new SensorLightOffEvent(objectId);
        boolean isOn = smartHome.findLightByID(objectId).getObject().isOn();
        assertTrue(isOn);
        event.handleEvent(smartHome);
        isOn = smartHome.findLightByID(objectId).getObject().isOn();
        assertFalse(isOn);
    }

    @Test
    void handleEventLightOffTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "4";
        SensorEvent event = new SensorLightOffEvent(objectId);
        boolean isOn = smartHome.findLightByID(objectId).getObject().isOn();
        assertFalse(isOn);
        event.handleEvent(smartHome);
        isOn = smartHome.findLightByID(objectId).getObject().isOn();
        assertFalse(isOn);
    }
}