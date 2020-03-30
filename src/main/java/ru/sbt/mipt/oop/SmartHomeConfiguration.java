package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.SensorEventHandlerAdapter;
import ru.sbt.mipt.oop.command.ProvisionalSensorCommandSender;
import ru.sbt.mipt.oop.command.SensorCommandSender;
import ru.sbt.mipt.oop.decorator.SecurityDecorator;
import ru.sbt.mipt.oop.event.SensorEventType;
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
import java.util.Map;


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
    SensorCommandSender commandSender() {
        return new ProvisionalSensorCommandSender();
    }

    @Bean
    public EventHandler eventDoorHandler(SmartHome smartHome) {
        return new EventDoorHandler(smartHome);
    }

    @Bean
    public EventHandler eventLightHandler(SmartHome smartHome) {
        return new EventLightHandler(smartHome);
    }

    @Bean
    public EventHandler eventHallDoorHandler(SmartHome smartHome, SensorCommandSender commandSender) {
        return new EventHallDoorHandler(smartHome, commandSender);
    }

    @Bean
    public List<EventHandler> eventHandlers(EventHandler eventDoorHandler, EventHandler eventLightHandler, EventHandler eventHallDoorHandler) {
        return Arrays.asList(
                eventDoorHandler,
                eventLightHandler,
                eventHallDoorHandler
        );
    }

    @Bean
    public Map<String, SensorEventType> convertType(){
        return Map.of(
                "LightIsOn", SensorEventType.LIGHT_ON,
                "LightIsOff", SensorEventType.LIGHT_OFF,
                "DoorIsOpen", SensorEventType.DOOR_OPEN,
                "DoorIsClosed", SensorEventType.DOOR_CLOSED
        );
    }

    @Bean
    public SensorEventsManager sensorEventsManager(SmartHome smartHome, SensorCommandSender commandSender) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(
                new SensorEventHandlerAdapter(
                        new SecurityDecorator(
                                eventHandlers(eventDoorHandler(smartHome),
                                              eventLightHandler(smartHome),
                                              eventHallDoorHandler(smartHome, commandSender)),
                                smartHome.getSignaling()),
                        convertType()
                )
        );
        return sensorEventsManager;
    }
}
