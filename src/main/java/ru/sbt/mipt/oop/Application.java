package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) {
        JsonSmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        SmartHome smartHome = smartHomeReader.read();

        // SRP & IOP & LSP
        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(),
                new DoorEventProcessor());

        // SRP
        SmartHomeEventHandler smartHomeEventHandler = new SmartHomeEventHandler(smartHome, eventProcessors);

        SensorEvent event;
        while (true) {
            event = EventGenerator.getNextSensorEvent();
            if (event == null) break;

            smartHomeEventHandler.handleEvent(event);
        }
    }

}
