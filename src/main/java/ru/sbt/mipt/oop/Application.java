package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.command.ProvisionalSensorCommandSender;
import ru.sbt.mipt.oop.eventhandler.EventDoorHandler;
import ru.sbt.mipt.oop.eventhandler.EventHallDoorHandler;
import ru.sbt.mipt.oop.eventhandler.EventLightHandler;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.storage.HomeConditionGsonStorage;
import ru.sbt.mipt.oop.provider.EventProvider;
import ru.sbt.mipt.oop.provider.EventRandomProvider;

import java.util.Arrays;


public class Application {
    private final EventsRegistrar eventsRegistrar;


    private Application(EventsRegistrar eventsRegistrar) {
        this.eventsRegistrar = eventsRegistrar;
    }

    public static void main(String... args) {
        String filename = "smart-home-1.js";
        SmartHome smartHome = new HomeConditionGsonStorage(filename).readHome();
        new Application(new EventsRegistrar(new EventRandomProvider(), Arrays.asList(new EventDoorHandler(smartHome), new EventLightHandler(smartHome), new EventHallDoorHandler(smartHome, new ProvisionalSensorCommandSender())))).handleEvents();
    }

    private void handleEvents() {
        eventsRegistrar.registerEvents();
    }
}
