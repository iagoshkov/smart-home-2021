package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.condition.HomeConditionImplementation;
import ru.sbt.mipt.oop.event_handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import java.io.IOException;
import java.util.ArrayList;


public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeConditionImplementation homeConditionImplementation = new HomeConditionImplementation();
        SmartHome smartHome = homeConditionImplementation.smartHomeCondition();
        // начинаем цикл обработки событий
        EventProcessor eventProcessor = new EventProcessor(smartHome, new EventGenerator(), new EventSolverImplementation(new ArrayList<>(), new LightEventHandler(), new DoorEventHandler(), new ClosedHallDoorEventHandler()));
        eventProcessor.processEvent();
    }
}
