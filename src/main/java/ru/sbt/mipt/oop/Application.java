package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {
    private final SmartHomeReaderWriter smartHomeReaderWriter;
    private final EventManager eventManager;

    public Application(SmartHomeReaderWriter smartHomeReaderWriter, EventManager eventManager) {
        this.smartHomeReaderWriter = smartHomeReaderWriter;
        this.eventManager = eventManager;
    }

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
//        SmartHomeJsonReaderWriter smartHomeReaderWriter = new SmartHomeJsonReaderWriter("smart-home-1.js", "output.js");
        SmartHomeJsonReaderWriter smartHomeReaderWriter = new SmartHomeJsonReaderWriter("output.js", "output.js");
        EventManager eventManager = new EventManager();
        Application application = new Application(smartHomeReaderWriter, eventManager);

        application.run();

    }

    public void run() throws IOException {
        SensorEvent event;
        SmartHome smartHome = smartHomeReaderWriter.loadSmartHome();

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
