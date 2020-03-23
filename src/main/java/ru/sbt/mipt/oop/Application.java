package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
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

import java.util.Arrays;
import java.util.List;


public class Application {
    public static void main(String... args) {
        String filename = "smart-home-1.js";
        SmartHome smartHome = new HomeConditionGsonStorage(filename).readHome();
        smartHome.addSignaling(new Signaling());

        SensorCommandSender commandSender = new ProvisionalSensorCommandSender();

        List<EventHandler> eventHandlers = Arrays.asList(new EventDoorHandler(smartHome),
                                                         new EventLightHandler(smartHome),
                                                         new EventHallDoorHandler(smartHome, commandSender));

        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new SensorEventHandlerAdapter(new SecurityDecorator(eventHandlers, smartHome.getSignaling())));
        sensorEventsManager.start();
    }

}
