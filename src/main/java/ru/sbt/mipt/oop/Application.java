package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventhandler.EventDoorHandler;
import ru.sbt.mipt.oop.eventhandler.EventHallDoorHandler;
import ru.sbt.mipt.oop.eventhandler.EventLightHandler;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.storage.HomeConditionGsonStorage;
import ru.sbt.mipt.oop.provider.EventProvider;
import ru.sbt.mipt.oop.provider.EventRandomProvider;

import java.util.Arrays;


public class Application {
    private final SmartHome smartHome;
    private final EventsRegistrar eventsRegistrar;


    private Application(SmartHome smartHome, EventProvider eventProvider) {
        this.smartHome = smartHome;
        this.eventsRegistrar = new EventsRegistrar(eventProvider, Arrays.asList(new EventDoorHandler(smartHome), new EventLightHandler(smartHome), new EventHallDoorHandler(smartHome)));
    }

    public static void main(String... args) {
        String filename = "smart-home-1.js";
        new Application(new HomeConditionGsonStorage(filename).readHome(), new EventRandomProvider()).handleEvents();
    }

    private void handleEvents() {
        eventsRegistrar.registerEvents();
    }
}
