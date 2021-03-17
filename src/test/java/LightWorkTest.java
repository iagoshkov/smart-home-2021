import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.handlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.handlers.SensorEventHandler;
import ru.sbt.mipt.oop.readers.JSONSmartHomeReader;
import ru.sbt.mipt.oop.readers.SmartHomeReader;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.File;

public class LightWorkTest {
    private static SmartHome smartHome;

    @BeforeAll
    static void init() {
        String filename = "./src/test/resources/smart-home-test.js";
        SmartHomeReader reader = new JSONSmartHomeReader(filename);
        smartHome = reader.readSmartHome();
    }

    @Test
    public void lightOffWork() {
        Light light = getLight("1");
        assertFalse(light.isOn());
    }

    @Test
    public void lightOnWork() {
        Light light = getLight("3");
        assertTrue(light.isOn());
    }

    @Test
    public void lightSwitchWork() {
        SensorEvent lightSwitch = new SensorEvent(SensorEventType.LIGHT_OFF,"3");
        SensorEventHandler eventHandler = new LightSensorEventHandler(smartHome, lightSwitch);
        eventHandler.handleEvent();

        Light light = getLight("3");
        assertFalse(light.isOn());
    }

    private Light getLight(String lightId) {
        Light lightResult = null;
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)) {
                    lightResult = light;
                }
            }
        }
        return lightResult;
    }
}
