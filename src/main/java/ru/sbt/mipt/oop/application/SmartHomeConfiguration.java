package ru.sbt.mipt.oop.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.smarthome.commands.DummyCommandSender;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.events.EventManager;
import ru.sbt.mipt.oop.smarthome.events.EventManagerCCAdapter;
import ru.sbt.mipt.oop.smarthome.events.decorators.IgnoringDecorator;
import ru.sbt.mipt.oop.smarthome.events.decorators.SMSNotifyingDecorator;
import ru.sbt.mipt.oop.smarthome.events.handlers.*;
import ru.sbt.mipt.oop.smarthome.services.logger.ConsoleLogger;
import ru.sbt.mipt.oop.smarthome.services.logger.Logger;
import ru.sbt.mipt.oop.smarthome.services.reader.SmartHomeJsonReader;
import ru.sbt.mipt.oop.smarthome.services.reader.SmartHomeReader;

import java.util.List;

@Configuration
public class SmartHomeConfiguration {
    @Bean
    SmartHomeReader reader() {
        return new SmartHomeJsonReader("smart-home-1.js");
    }

    @Bean
    Alarm alarm() {
        return new Alarm();
    }

    @Bean
    Logger logger() {
        return new ConsoleLogger();
    }

    @Bean
    SmartHome smartHome() {
        SmartHome smartHome = reader().read();
        smartHome.setAlarm(alarm());
        return smartHome;
    }

    @Bean
    EventManager eventManager(List<EventHandler> handlers) {
        return new EventManagerCCAdapter(handlers);
    }

    @Bean
    EventHandler alarmHandler() {
        return new SMSNotifyingDecorator(new AlarmStateEventHandler(smartHome(), logger()), alarm());
    }

    @Bean
    EventHandler lightHandler() {
        return new IgnoringDecorator(new LightEventHandler(smartHome(), logger()), alarm());

    }

    @Bean
    EventHandler doorHandler() {
        return new IgnoringDecorator(new DoorEventHandler(smartHome(), logger()), alarm());
    }

    @Bean
    EventHandler hallDoorHandler() {
        return new IgnoringDecorator(
                new HallDoorClosedEventHandler(smartHome(), new DummyCommandSender(), logger()),
                alarm()
        );
    }
}
