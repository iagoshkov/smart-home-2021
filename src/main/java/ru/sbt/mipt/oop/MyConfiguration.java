package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.adpter.EventHandlerAdapter;
import ru.sbt.mipt.oop.events.processors.*;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeReaderJsonFile;

import java.util.Collection;

@Configuration
public class MyConfiguration {
    @Bean
    EventProcessor alarmEventProcessor(){
        return new AlarmEventProcessor();
    }

    @Bean
    EventProcessor doorEventProcessor(){
        return new DoorEventProcessor();
    }

    @Bean
    EventProcessor hallDoorEventProcessor(){
        return new HallDoorEventProcessor();
    }

    @Bean
    EventProcessor lightEventProcessor(){
        return new LightEventProcessor();
    }

    @Bean
    SmartHome smartHome() {
        return new SmartHomeReaderJsonFile(Constants.INPUT_SMART_HOME_JSON_FILE_NAME).load();
    }

    @Bean
    SensorEventsManager sensorEventsManager(Collection<EventProcessor> eventProcessors, SmartHome smartHome) {
        EventHandlerAdapter adapter = new EventHandlerAdapter(new EventHandler(eventProcessors, smartHome));
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(adapter);
        return sensorEventsManager;
    }
}
