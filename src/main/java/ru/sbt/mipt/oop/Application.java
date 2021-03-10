package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHomeRecorder homeReader = new JsonSmartHomeRecorder();
        SmartHome smartHome = homeReader.getSmartHome("smart-home-1.js");
        // начинаем цикл обработки событий
        EventsSource eventsSource = new TestingEventsSource();
        OutputStream output = new OutputStreamImpl();
        ReceiverSmartHomeEvents receiver = new ReceiverSmartHomeEventsImpl();
        receiver.handleEvents(eventsSource, smartHome, output);
    }
}
