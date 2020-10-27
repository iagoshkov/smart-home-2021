package ru.sbt.mipt.oop.spring.utils;


import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.api.adapter.Adapter;
import ru.sbt.mipt.oop.api.adapter.DoorSensorEventGetterImpl;
import ru.sbt.mipt.oop.api.adapter.LightSensorEventGetterImpl;
import ru.sbt.mipt.oop.api.adapter.SensorEventGetter;
import ru.sbt.mipt.oop.condition.HomeConditionImplementation;
import ru.sbt.mipt.oop.event_handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;

@Configuration
public class SpringConfiguration {

    @Bean
    GeneralEvent doorEvent(){
        return new DoorEventHandler();
    }

    @Bean
    GeneralEvent lightEvent(){
        return new LightEventHandler();
    }

    @Bean
    SensorEventGetter doorSensorEventGetter(){
        return new DoorSensorEventGetterImpl();
    }

    @Bean
    SensorEventGetter lightSensorEventGetter(){
        return new LightSensorEventGetterImpl();
    }

    @Bean
    EventSolverImplementation eventSolver(){
        return new EventSolverImplementation();
    }

    @Bean
    Adapter eventSolverImplementationAdapter(EventSolverImplementation eventSolverImplementation, SmartHome smartHome){
        return new Adapter(eventSolverImplementation, smartHome);
    }

    @Bean
    SmartHome smartHome() {
        return new HomeConditionImplementation().smartHomeCondition();
    }

    @Bean
    SensorEventsManager sensorEventsManager(Adapter adapter) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(adapter);
        return sensorEventsManager;
    }

    @Bean
    EventGenerator eventGenerator(){
        return new EventGenerator();
    }

    @Bean
    EventProcessor eventProcessor(SmartHome smartHome, EventSolverImplementation eventSolverImplementation, EventGenerator eventGenerator){
        return new EventProcessor(smartHome, eventSolverImplementation, eventGenerator);
    }
}
