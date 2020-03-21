package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.command.ProvisionalSensorCommandSender;
import ru.sbt.mipt.oop.decorator.SecurityDecorator;
import ru.sbt.mipt.oop.eventhandler.EventDoorHandler;
import ru.sbt.mipt.oop.eventhandler.EventHallDoorHandler;
import ru.sbt.mipt.oop.eventhandler.EventLightHandler;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.signaling.Signaling;
import ru.sbt.mipt.oop.storage.HomeConditionGsonStorage;
import ru.sbt.mipt.oop.provider.EventRandomProvider;

import java.util.Arrays;
import java.util.Collections;


public class Application {
    private final EventsRegistrar eventsRegistrar;


    private Application(EventsRegistrar eventsRegistrar) {
        this.eventsRegistrar = eventsRegistrar;
    }

    public static void main(String... args) {
        String filename = "smart-home-1.js";
        SmartHome smartHome = new HomeConditionGsonStorage(filename).readHome();
        smartHome.addSignaling(new Signaling());
        new Application(new EventsRegistrar(new EventRandomProvider(), Collections.singletonList(new SecurityDecorator(Arrays.asList(new EventDoorHandler(smartHome), new EventLightHandler(smartHome), new EventHallDoorHandler(smartHome, new ProvisionalSensorCommandSender())), smartHome.getSignaling(new Signaling()))))).handleEvents();
    }

    private void handleEvents() {
        eventsRegistrar.registerEvents();
    }
}
