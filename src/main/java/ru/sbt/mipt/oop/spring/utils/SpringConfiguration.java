package ru.sbt.mipt.oop.spring.utils;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.api.adapter.Adapter;
import ru.sbt.mipt.oop.condition.HomeConditionImplementation;
import ru.sbt.mipt.oop.event_handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;

@Configuration
public class SpringConfiguration {

    @Bean
    EventSolverWithEvents eventSolver(){
        return new EventSolverImplementation();
    }

    @Bean
    Adapter eventSolverImplementationAdapter(){
        return new Adapter(eventSolver(), smartHome());
    }

    @Bean
    SmartHome smartHome() {
        return new HomeConditionImplementation().smartHomeCondition();
    }

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventSolverImplementationAdapter());
        return sensorEventsManager;
    }

    @Bean
    EventGenerator eventGenerator(){
        return new EventGenerator();
    }

    @Bean
    EventProcessor eventProcessor(){
        return new EventProcessor(smartHome(), eventSolver(), eventGenerator());
    }

}
