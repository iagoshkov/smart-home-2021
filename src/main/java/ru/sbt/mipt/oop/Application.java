package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.command.LightOffCommandProducer;
import ru.sbt.mipt.oop.event.handler.EventHandler;
import ru.sbt.mipt.oop.event.handler.SensorEventHandler;
import ru.sbt.mipt.oop.event.processor.DoorEventProcessor;
import ru.sbt.mipt.oop.event.processor.EventProcessor;
import ru.sbt.mipt.oop.event.processor.HallDoorEventProcessor;
import ru.sbt.mipt.oop.event.processor.LightEventProcessor;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        SmartHome smartHome = smartHomeReader.read();

        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(),
                new DoorEventProcessor(),
                new HallDoorEventProcessor(new LightOffCommandProducer())
        );

        EventHandler eventHandler = new SensorEventHandler(smartHome, eventProcessors);

        SmartHomeSimulator.simulateWork(eventHandler);
    }

}
