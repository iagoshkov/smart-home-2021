package ru.sbt.mipt.oop;

import org.junit.Test;

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

        eventHandler = new SmartHomeSensorEventHandler(smartHome, eventProcessors);
    }

    @Test
    public void doorEventsTest() {
        DoorEventsTest doorEventsTest = new DoorEventsTest();

        doorEventsTest.applyOnExistingDoorTest();
        doorEventsTest.applyOnNonExistingDoorTest();
    }

    @Test
    public void lightEventsTest() {
        LightEventsTest lightEventsTest = new LightEventsTest();

        lightEventsTest.applyOnExistingLightTest();
        lightEventsTest.applyOnNonExistingLightTest();
    }

    @Test
    public void hallDoorClosedTest() {
        HallDoorClosedTest hallDoorClosedTest = new HallDoorClosedTest();

        hallDoorClosedTest.closeHallDoorTest();
        hallDoorClosedTest.closeNotHallDoorTest();
    }

}
