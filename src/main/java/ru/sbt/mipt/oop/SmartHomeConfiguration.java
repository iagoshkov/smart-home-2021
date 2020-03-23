package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.SensorEventHandlerAdapter;
import ru.sbt.mipt.oop.command.ProvisionalSensorCommandSender;
import ru.sbt.mipt.oop.command.SensorCommandSender;
import ru.sbt.mipt.oop.decorator.SecurityDecorator;
import ru.sbt.mipt.oop.eventhandler.EventDoorHandler;
import ru.sbt.mipt.oop.eventhandler.EventHallDoorHandler;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.eventhandler.EventLightHandler;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signaling.Signaling;
import ru.sbt.mipt.oop.storage.HomeConditionGsonStorage;
import ru.sbt.mipt.oop.storage.HomeConditionStorage;

import java.util.Arrays;
import java.util.List;


@Configuration
public class SmartHomeConfiguration {
    @Bean
    public HomeConditionStorage homeConditionStorage() {
        String filename = "smart-home-1.js";
        return new HomeConditionGsonStorage(filename);
    }

    @Bean
    public SmartHome smartHome(HomeConditionStorage homeConditionStorage) {
        SmartHome smartHome = homeConditionStorage.readHome();
        smartHome.addSignaling(new Signaling());
        return smartHome;
    }

    @Bean
    public SensorEventsManager sensorEventsManager(SmartHome smartHome) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        SensorCommandSender commandSender = new ProvisionalSensorCommandSender();

        List<EventHandler> eventHandlers = Arrays.asList(new EventDoorHandler(smartHome),
                                                         new EventLightHandler(smartHome),
                                                         new EventHallDoorHandler(smartHome, commandSender));

        sensorEventsManager.registerEventHandler(new SensorEventHandlerAdapter(new SecurityDecorator(eventHandlers,
                                                                                smartHome.getSignaling())));

        return sensorEventsManager;
    }
}
