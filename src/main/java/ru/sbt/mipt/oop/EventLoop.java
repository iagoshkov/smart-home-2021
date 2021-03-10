package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class EventLoop {
    EventLoop(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    void process(){
        // создаём обработчик
        LightEventHandler lightEventHandler = new LightEventHandler(smartHome);
        DoorEventHandler doorEventHandler = new DoorEventHandler(smartHome);
        // начинаем цикл обработки событий
        RandomSensorEventGenerator generator = new RandomSensorEventGenerator();
        SensorEvent event = generator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                // событие от источника света
                lightEventHandler.handleEvent(event);
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                // событие от двери
                doorEventHandler.handleEvent(event);
            }
            event = generator.getNextSensorEvent();
        }
    }

    SmartHome smartHome;
}
