package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.command.LightOffCommandProducer;
import ru.sbt.mipt.oop.event.processor.SensorEventProcessor;
import ru.sbt.mipt.oop.event.handler.*;
import ru.sbt.mipt.oop.event.handler.DoorEventHandler;
import ru.sbt.mipt.oop.event.handler.LightEventHandler;
import ru.sbt.mipt.oop.util.SmartHomeTestComponent;

import java.util.Arrays;
import java.util.List;

public class SmartHomeTest extends SmartHomeTestComponent {

    public SmartHomeTest() {
        smartHome = new SmartHomeCreator().create();

        List<EventHandler> eventHandlers = Arrays.asList(
                new LightEventHandler(smartHome),
                new DoorEventHandler(smartHome),
                new HallDoorEventHandler(smartHome, new LightOffCommandProducer())
        );

        eventProcessor = new SensorEventProcessor(smartHome, eventHandlers);
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
