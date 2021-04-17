package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.command.LightOffCommandProducer;
import ru.sbt.mipt.oop.event.processor.EventProcessor;
import ru.sbt.mipt.oop.event.processor.SensorEventProcessor;
import ru.sbt.mipt.oop.event.handler.*;
import ru.sbt.mipt.oop.event.handler.DoorEventHandler;
import ru.sbt.mipt.oop.event.handler.EventHandler;
import ru.sbt.mipt.oop.io.JsonSmartHomeReader;
import ru.sbt.mipt.oop.io.SmartHomeReader;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String... args) {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        SmartHome smartHome = smartHomeReader.read();

        List<EventHandler> eventHandlers = Arrays.asList(
                new LightEventHandler(smartHome),
                new DoorEventHandler(smartHome),
                new HallDoorEventHandler(smartHome, new LightOffCommandProducer())
        );

        EventProcessor eventProcessor = new SensorEventProcessor(smartHome, eventHandlers);

        SmartHomeSimulator.simulateWork(eventProcessor);
    }
}
