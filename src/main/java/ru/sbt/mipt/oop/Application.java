package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.condition.HomeConditionImplementation;
import ru.sbt.mipt.oop.event_handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeConditionImplementation homeConditionImplementation = new HomeConditionImplementation();
        SmartHome smartHome = homeConditionImplementation.smartHomeCondition();
        // начинаем цикл обработки событий
        List<GeneralEvent> eventHandlersList = Arrays.asList(new LightEventHandler(), new DoorEventHandler(), new ClosedHallDoorEventHandler());
        EventProcessor eventProcessor = new EventProcessor(smartHome, new EventGenerator(), new EventSolverImplementation(eventHandlersList));
        eventProcessor.processEvent();
    }
}
