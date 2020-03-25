package ru.sbt.mipt.oop.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.smarthome.events.EventManager;

import java.io.IOException;

public class Application {
    public static void main(String... args) throws IOException {
//        Alarm alarm = new Alarm();
//        SmartHome smartHome = new SmartHomeJsonReader("smart-home-1.js").read();
//        smartHome.setAlarm(alarm);
//        Logger logger = new ConsoleLogger();
//        CommandSender sender = new DummyCommandSender();
//
//        List<EventHandler> handlers = Arrays.asList(
//                new IgnoringDecorator(new DoorEventHandler(smartHome, logger), alarm),
//                new IgnoringDecorator(new LightEventHandler(smartHome, logger), alarm),
//                new IgnoringDecorator(new HallDoorClosedEventHandler(smartHome, sender, logger), alarm),
//                new SMSNotifyingDecorator(new AlarmStateEventHandler(smartHome, logger), alarm)
//        );
//
//        EventProvider provider = new RandomEventProvider();
//        EventManager manager = new EventManagerCCAdapter(handlers);
//        manager.runCycle();
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        EventManager eventManager = context.getBean(EventManager.class);
        eventManager.runCycle();

    }
}
