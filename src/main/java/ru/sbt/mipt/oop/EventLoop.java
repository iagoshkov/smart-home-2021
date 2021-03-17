package ru.sbt.mipt.oop;

import java.util.ArrayList;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class EventLoop {
    EventLoop(SmartHome smartHome, ArrayList<EventHandler> handlers){
        this.smartHome = smartHome;
        this.handlers = handlers;
    }

    void process(){
        // создаём обработчик

        // начинаем цикл обработки событий
        RandomSensorEventGenerator generator = new RandomSensorEventGenerator();
        SensorEvent event = generator.getNextSensorEvent();
        while (event != null) {
            for (EventHandler handler:handlers){
                handler.handleEvent(event);
            }
            event = generator.getNextSensorEvent();
        }
    }

    SmartHome smartHome;
    ArrayList<EventHandler> handlers;
}
