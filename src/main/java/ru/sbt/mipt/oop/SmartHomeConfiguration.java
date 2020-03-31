package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
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
import ru.sbt.mipt.oop.rc.*;
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
    public String code() {
        return "12345";
    }

    @Bean
    public Command activateSignalingCommand(SmartHome smartHome, String code) {
        return new ActivateSignalingCommand(smartHome, code);
    }

    @Bean
    public Command closeHallDoorCommand(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }

    @Bean
    public Command turnOnHallLightCommand(SmartHome smartHome) {
        return new TurnOnHallLightCommand(smartHome);
    }

    @Bean
    public Command turnOnAlarmCommand(SmartHome smartHome) {
        return new TurnOnAlarmCommand(smartHome);
    }

    @Bean
    public Command turnOnLightCommand(SmartHome smartHome) {
        return new TurnOnLightCommand(smartHome);
    }

    @Bean
    public Command turnOffLightCommand(SmartHome smartHome) {
        return new TurnOffLightCommand(smartHome);
    }

    @Bean
    public RemoteControl remoteControl(Command turnOffLightCommand, Command turnOnLightCommand,
                                       Command turnOnAlarmCommand, Command turnOnHallLightCommand,
                                       Command activateSignalingCommand, Command closeHallDoorCommand) {
        SmartRemoteControl remoteControl = new SmartRemoteControl();
        remoteControl.set("A", turnOffLightCommand);
        remoteControl.set("B", turnOnLightCommand);
        remoteControl.set("C", turnOnAlarmCommand);
        remoteControl.set("D", turnOnHallLightCommand);
        remoteControl.set("1", activateSignalingCommand);
        remoteControl.set("2", closeHallDoorCommand);
        return remoteControl;
    }

    @Bean
    public String rcId() {
        return "newRemoteControl";
    }

    @Bean
    public RemoteControlRegistry remoteControlRegistry(RemoteControl remoteControl, String rcId) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl, rcId);
        return remoteControlRegistry;
    }

    @Bean
    public SensorEventsManager sensorEventsManager(SmartHome smartHome, List<EventHandler> eventHandlers) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(
                new SensorEventHandlerAdapter(
                        new SecurityDecorator(
                                eventHandlers,
                                smartHome.getSignaling()),
                        convertType()
                )
        );
        return sensorEventsManager;
    }
}
