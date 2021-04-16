package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.command.LightOffCommandProducer;
import ru.sbt.mipt.oop.event.handler.SensorEventHandler;
import ru.sbt.mipt.oop.event.processor.DoorEventProcessor;
import ru.sbt.mipt.oop.event.processor.EventProcessor;
import ru.sbt.mipt.oop.event.processor.HallDoorEventProcessor;
import ru.sbt.mipt.oop.event.processor.LightEventProcessor;
import ru.sbt.mipt.oop.util.SmartHomeTestComponent;

import java.util.Arrays;
import java.util.List;

public class SmartHomeTest extends SmartHomeTestComponent {

    public SmartHomeTest() {
        smartHome = new SomeSmartHomeCreator().create();

        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(),
                new DoorEventProcessor(),
                new HallDoorEventProcessor(new LightOffCommandProducer())
        );

        eventHandler = new SensorEventHandler(smartHome, eventProcessors);
    }

    @Test
    public void doorEvents() {
        DoorEventsTest doorEventsTest = new DoorEventsTest();

        doorEventsTest.applyOnExistingDoor();
        doorEventsTest.applyOnNonExistingDoor();
    }

    @Test
    public void lightEvents() {
        LightEventsTest lightEventsTest = new LightEventsTest();

        lightEventsTest.applyOnExistingLight();
        lightEventsTest.applyOnNonExistingLight();
    }

    @Test
    public void hallDoorClosed() {
        HallDoorClosedTest hallDoorClosedTest = new HallDoorClosedTest();

        hallDoorClosedTest.closeHallDoor();
        hallDoorClosedTest.closeNotHallDoor();
    }

}
