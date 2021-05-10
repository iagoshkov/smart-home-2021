package ru.sbt.mipt.oop;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.Handler.Decorator.AlarmSensorEventHandler;
import ru.sbt.mipt.oop.Handler.*;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.SmartHome;
import ru.sbt.mipt.oop.Reader.SmartHomeReader;
import ru.sbt.mipt.oop.Adapter.*;
import ru.sbt.mipt.oop.Type.DoorEventType;
import ru.sbt.mipt.oop.Type.LightEventType;


@Configuration
public class MyConfiguration {

    @Bean
    SmartHome smartHome() {
        return new SmartHomeReader().getSmartHomeFromJson("output.js");
    }

    @Bean
    SmartAlarm smartAlarm() {
        return new SmartAlarm();
    }

    @Bean
    CCSensorEventAdapter ccSensorEventAdapter(SmartHome smartHome, SmartAlarm smartAlarm) {
        Map<String, CCEventCreator> map = Map.of(
               "LightIsOn", new CCLightEventCreator(LightEventType.LIGHT_ON),
                "LightIsOff", new CCLightEventCreator(LightEventType.LIGHT_OFF),
                "DoorIsOpen", new CCDoorEventCreator(DoorEventType.DOOR_OPEN),
                "DoorIsClosed", new CCDoorEventCreator(DoorEventType.DOOR_CLOSED)
        );
        return new CCSensorEventAdapter(smartHome, smartAlarm, map, handlers(smartHome, smartAlarm));
    }
    
        @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager manager = new SensorEventsManager();

        manager.registerEventHandler(ccSensorEventAdapter(smartHome(), smartAlarm()));

        return manager;
    }

    @Bean
    List<SensorEventHandler> handlers(SmartHome smartHome, SmartAlarm alarm) {
        List<SensorEventHandler> sensorEventHandlers = Arrays.asList(new DoorEventHandler(smartHome), new LightEventHandler(smartHome), new HallDoorEventHandler(smartHome));

        return Arrays.asList(new AlarmSensorEventHandler(alarm, sensorEventHandlers), new AlarmEventHandler(alarm));
    }
}
