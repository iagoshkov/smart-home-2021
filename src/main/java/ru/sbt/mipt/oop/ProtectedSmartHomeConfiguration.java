package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.rc.*;

import java.util.List;
import java.util.Map;

import static ru.sbt.mipt.oop.SensorEventType.*;

@Configuration
public class ProtectedSmartHomeConfiguration {
    @Bean
    public SmartHome smartHome() {
        return new SmartHomeJsonReader().readSmartHome("smart-home-1.js");
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
    Map<String, SensorEventType> ccStringToType() {
        return Map.ofEntries(
                Map.entry("LightIsOn", LIGHT_ON),
                Map.entry("LightIsOff", LIGHT_OFF),
                Map.entry("DoorIsOpen", DOOR_OPEN),
                Map.entry("DoorIsClosed", DOOR_CLOSED)
        );
    }

    @Bean
    EventProcessor lightEventProcessor(){
        return new LightEventProcessor();
    }


    @Bean
    EventProcessor alarmEventProcessor(){
        return new AlarmEventProcessor();
    }

    @Bean
    CCEventProcessorAdapter eventProcessorAdapter(List<EventProcessor> processors, SmartHome smartHome,
                                                  Map<String, SensorEventType> ccStringToType){
        return new CCEventProcessorAdapter(new AlarmEventProcessorDecorator(processors), smartHome, ccStringToType);
    }

    @Bean
    public SensorEventCreator sensorEventsManager(CCEventProcessorAdapter adapter) {
        SensorEventCreator sensorEventsManager = new SensorEventCreator();
        sensorEventsManager.registerEventProcessor(adapter);
        return sensorEventsManager;
    }

    @Bean
    String code() {
        return "activate";
    }

    @Bean
    Command activateAlarmCommand(SmartHome smartHome) {
        return new ActivateAlarmCommand(smartHome, code());
    }

    @Bean
    Command closeHallDoorCommand(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }

    @Bean
    Command turnOffAllLightsCommand(SmartHome smartHome) {
        return new TurnOffAllLightsCommand(smartHome);
    }

    @Bean
    Command turnOnAlertCommand(SmartHome smartHome) {
        return new TurnOnAlertCommand(smartHome);
    }

    @Bean
    Command turnOnAllLightsCommand(SmartHome smartHome) {
        return new TurnOnAllLightsCommand(smartHome);
    }

    @Bean
    Command turnOnHallLightCommand(SmartHome smartHome) {
        return new TurnOnHallLightCommand(smartHome);
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry(RemoteControl controller, String rcId) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(controller, rcId);
        return remoteControlRegistry;
    }

    @Bean
    RemoteControl remoteControl(SmartHome smartHome) {
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand("0", turnOffAllLightsCommand(smartHome));
        remoteControl.setCommand("1", turnOnAllLightsCommand(smartHome));
        remoteControl.setCommand("2", turnOnHallLightCommand(smartHome));
        remoteControl.setCommand("3", activateAlarmCommand(smartHome));
        remoteControl.setCommand("4", closeHallDoorCommand(smartHome));
        remoteControl.setCommand("5", turnOnAlertCommand(smartHome));
        return remoteControl;
    }
}
