package ru.sbt.mipt.oop.configurations;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.command.TurnOffLightCommandProducer;
import ru.sbt.mipt.oop.event.handler.*;
import ru.sbt.mipt.oop.io.JsonSmartHomeReader;
import ru.sbt.mipt.oop.io.SmartHomeReader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ApplicationConfiguration {

    @Bean
    SmartHome getSmartHome() {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        return smartHomeReader.read();
    }

    @Bean
    LightEventHandler getLightEventHandler() {
        return new LightEventHandler(getSmartHome());
    }

    @Bean
    DoorEventHandler getDoorEventHandler() {
        return new DoorEventHandler(getSmartHome());
    }

    @Bean
    HallDoorEventHandler getHallDoorEventHandler() {
        return new HallDoorEventHandler(getSmartHome(), new TurnOffLightCommandProducer());
    }

    @Bean
    List<EventHandler> getSensorEventHandlers() {
        return Arrays.asList(
                getLightEventHandler(),
                getDoorEventHandler(),
                getHallDoorEventHandler()
        );
    }

    @Bean
    List<com.coolcompany.smarthome.events.EventHandler> getAdaptedSensorEventHandlers(List<EventHandler> eventHandlers) {
        return eventHandlers.stream().map(SensorEventHandlerAdapter::new).collect(Collectors.toList());
    }

    @Bean
    SensorEventsManager getSensorEventsManager(List<com.coolcompany.smarthome.events.EventHandler> eventHandlers) {
        SensorEventsManager eventsManager = new SensorEventsManager();
        eventHandlers.forEach(eventsManager::registerEventHandler);
        return eventsManager;
    }

}
