import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.sensorEvent.SensorEvent;
import ru.sbt.mipt.oop.sensorEvent.SensorHallDoorClosedEvent;


import static org.junit.jupiter.api.Assertions.*;

class SensorHallDoorClosedEventTest {

    @Test
    void handleEventClosedHallDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "4";
        SensorEvent event = new SensorHallDoorClosedEvent(objectId);
        assertFalse(smartHome.findDoorByID(objectId).getObject().isOpen());
        event.handleEvent(smartHome);
        assertFalse(smartHome.findDoorByID(objectId).getObject().isOpen());
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }
    }

    @Test
    void handleEventClosedNotHallDoorTest() throws CloneNotSupportedException {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String doorId = "3";
        String lightId = "3";
        SensorEvent event = new SensorHallDoorClosedEvent(doorId);
        assertTrue(smartHome.findDoorByID(doorId).getObject().isOpen());
        boolean isOn = smartHome.findLightByID(lightId).getObject().isOn();
        assertTrue(isOn);
        event.handleEvent(smartHome);
        assertFalse(smartHome.findDoorByID(doorId).getObject().isOpen());
        assertTrue(smartHome.findLightByID(lightId).getObject().isOn());
    }
}