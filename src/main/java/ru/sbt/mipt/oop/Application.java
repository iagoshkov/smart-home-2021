package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.config.loaders.ISmartHomeLoader;
import ru.sbt.mipt.oop.config.loaders.JsonSmartHomeLoader;

import java.io.IOException;

public class Application {
    private final ISmartHomeLoader smartHomeLoader;
    private final EventManager eventManager;

    public Application(ISmartHomeLoader smartHomeReader, EventManager eventManager) {
        this.smartHomeLoader = smartHomeReader;
        this.eventManager = eventManager;
    }

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        ISmartHomeLoader smartHomeReader = new JsonSmartHomeLoader("smart-home-1.js");
        EventManager eventManager = new EventManager();
        Application application = new Application(smartHomeReader, eventManager);

        application.run();
    }

    public void run() throws IOException {
        SensorEvent event;
        SmartHome smartHome = smartHomeLoader.load();

        eventManager.setSmartHome(smartHome);

        while (true) {
            // начинаем цикл обработки событий
            event = eventManager.getNextSensorEvent();

            if (event == null) {
                return;
            }
            eventManager.processEvent(event);
        }
    }


}
