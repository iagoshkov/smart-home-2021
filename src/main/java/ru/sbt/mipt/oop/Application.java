package ru.sbt.mipt.oop;
import ru.sbt.mipt.oop.events.processors.AnyEventProcessor;
import ru.sbt.mipt.oop.homereader.SmartHomeFromJSReader;


public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        SmartHome smartHome = (new SmartHomeFromJSReader("smart-home-1.js")).read();
        // начинаем цикл обработки событий
        EventLoop eventLoop = new EventLoop(new AnyEventProcessor(smartHome));
        eventLoop.startLoop();
    }
}
