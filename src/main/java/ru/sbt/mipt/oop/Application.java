package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.condition.HomeConditionImplementation;
import ru.sbt.mipt.oop.event_handlers.EventGenerator;
import ru.sbt.mipt.oop.event_handlers.EventProcessor;
import ru.sbt.mipt.oop.event_handlers.EventSolverDecorator;
import ru.sbt.mipt.oop.event_handlers.EventSolverImplementation;
import ru.sbt.mipt.oop.home.SmartHome;
import java.io.IOException;


public class Application {

    public static void main(String... args){
        // считываем состояние дома из файла
        HomeConditionImplementation homeConditionImplementation = new HomeConditionImplementation();
        SmartHome smartHome = homeConditionImplementation.smartHomeCondition();
        smartHome.setAlarm();
        // начинаем цикл обработки событий
        EventProcessor eventProcessor = new EventProcessor(smartHome, new EventSolverImplementation(), new EventGenerator());
        eventProcessor.processEvent();
    }
}
