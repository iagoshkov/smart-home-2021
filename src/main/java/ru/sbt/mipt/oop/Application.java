package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Application {

    public static void main(String... args) {
        JsonSmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        SmartHome smartHome = smartHomeReader.read();

        HashMap<SensorEventType, EventHandler> LightEventHandlers = new HashMap<SensorEventType, EventHandler>() {{
            put(SensorEventType.LIGHT_ON, new LightOnHandler());
            put(SensorEventType.LIGHT_OFF, new LightOffHandler());
        }};

        HashMap<SensorEventType, EventHandler> DoorEventHandlers = new HashMap<SensorEventType, EventHandler>() {{
            put(SensorEventType.DOOR_OPEN, new DoorOpenHandler());
            put(SensorEventType.DOOR_CLOSED, new DoorClosedHandler());
        }};

        HashMap<CommandType, CommandProducer> DoorCommandProducers = new HashMap<CommandType, CommandProducer>() {{
            put(CommandType.LIGHT_OFF, new LightOffCommandProducer());
        }};

        // SRP & IOP & LSP
        List<EventProcessor> eventProcessors = Arrays.asList(
            new LightEventProcessor(LightEventHandlers, new HashMap<>()),
            new DoorEventProcessor(DoorEventHandlers, DoorCommandProducers)
        );

        // SRP
        SmartHomeEventHandler smartHomeEventHandler = new SmartHomeEventHandler(smartHome, eventProcessors);

        SmartHomeSimulator.simulateWork(smartHomeEventHandler);
    }

}
