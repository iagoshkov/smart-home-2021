package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.config.loaders.ISmartHomeLoader;
import ru.sbt.mipt.oop.config.loaders.JsonSmartHomeLoader;
import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.LightEventHandler;
import ru.sbt.mipt.oop.events.manager.EventManager;
import ru.sbt.mipt.oop.events.manager.IEventManager;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.sensor.senders.ISensorEventSender;
import ru.sbt.mipt.oop.sensor.senders.RandomSensorEventSender;

import java.io.IOException;

public class Application {
    private final ISmartHomeLoader smartHomeLoader;
    private final IEventManager eventManager;
    private final ISensorEventSender sensorEventSender;

    public Application(ISmartHomeLoader smartHomeReader, IEventManager eventManager, ISensorEventSender sensorEventSender) {
        this.smartHomeLoader = smartHomeReader;
        this.eventManager = eventManager;
        this.sensorEventSender = sensorEventSender;
    }

    public void run() throws IOException {
        SensorEvent event;
        SmartHome smartHome = smartHomeLoader.load();

        eventManager.setSmartHome(smartHome);

        while (true) {
            // начинаем цикл обработки событий
            event = sensorEventSender.getNextSensorEvent();

            if (event == null) {
                return;
            }
            eventManager.processEvent(event);
        }
    }

    public static void main(String... args) throws IOException {
        String INPUT_CONFIG_FILE = "smart-home-1.js";

        // считываем состояние дома из файла
        ISmartHomeLoader smartHomeReader = new JsonSmartHomeLoader(INPUT_CONFIG_FILE);
        IEventManager eventManager = new EventManager();
        eventManager.addHandler(new LightEventHandler());
        eventManager.addHandler(new DoorEventHandler());
        eventManager.addHandler(new HallDoorEventHandler());
        ISensorEventSender sensorEventSender = new RandomSensorEventSender();

        Application application = new Application(smartHomeReader, eventManager, sensorEventSender);
        application.run();
    }

}
