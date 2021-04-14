package ru.sbt.mipt.oop;

import org.junit.Test;
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

        eventHandler = new SmartHomeSensorEventHandler(smartHome, eventProcessors);
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
