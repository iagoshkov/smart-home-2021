package ru.sbt.mipt.oop;

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

        SmartHomeEventHandler smartHomeEventHandler = new SmartHomeSensorEventHandler(smartHome, eventProcessors);

        SmartHomeSimulator.simulateWork(smartHomeEventHandler);
    }

}
