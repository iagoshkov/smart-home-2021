package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) {
        JsonSmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.json");
        SmartHome smartHome = smartHomeReader.read();

        listenToEvents(smartHome);
    }

    private static void listenToEvents(SmartHome smartHome) {
        for (SensorEvent event = getNextSensorEvent(); event != null; event = getNextSensorEvent()) {
            System.out.println("Got event: " + event);

            // SRP & IOP & LSP
            List<EventProcessor> eventProcessors = Arrays.asList(
                    new LightEventProcessor(smartHome),
                    new DoorEventProcessor(smartHome));
            // It may be a member of a class, but I thing it would be a higher coupling and loosely cohesion,
            // because CommandProducer only a producer that moreover know about CommandProcessor.
            // And I think Application would have these responsibilities
            CommandProducer commandProducer = new CommandProducer(smartHome);

            for (EventProcessor eventProcessor : eventProcessors) {
                List<CommandType> commandTypes = eventProcessor.processEvent(event);
                for (CommandType commandType : commandTypes) {
                    commandProducer.produceCommand(commandType);
                }
            }
        }
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
