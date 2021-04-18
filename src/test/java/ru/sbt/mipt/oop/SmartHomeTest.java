package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.command.TurnOffLightCommandProducer;
import ru.sbt.mipt.oop.event.handler.DoorEventHandler;
import ru.sbt.mipt.oop.event.handler.EventHandler;
import ru.sbt.mipt.oop.event.handler.HallDoorEventHandler;
import ru.sbt.mipt.oop.event.handler.LightEventHandler;
import ru.sbt.mipt.oop.event.processor.SensorEventProcessor;

import java.util.Arrays;
import java.util.List;

public class SmartHomeTest extends SmartHomeTestComponent {

    public SmartHomeTest() {
        smartHome = new SmartHomeCreator().create();

        List<EventHandler> eventHandlers = Arrays.asList(
                new LightEventHandler(smartHome),
                new DoorEventHandler(smartHome),
                new HallDoorEventHandler(smartHome, new TurnOffLightCommandProducer())
        );

        eventProcessor = new SensorEventProcessor(smartHome, eventHandlers);
    }

    @Test
    public void doorEvents() {
        DoorEventsTest doorEventsTest = new DoorEventsTest();
        doorEventsTest.set(smartHome, eventProcessor);

        doorEventsTest.applyOnExistingDoor();
        doorEventsTest.applyOnNonExistingDoor();
    }

    @Test
    public void lightEvents() {
        LightEventsTest lightEventsTest = new LightEventsTest();
        lightEventsTest.set(smartHome, eventProcessor);

        lightEventsTest.applyOnExistingLight();
        lightEventsTest.applyOnNonExistingLight();
    }

    @Test
    public void hallDoorClosed() {
        HallDoorClosedTest hallDoorClosedTest = new HallDoorClosedTest();
        hallDoorClosedTest.set(smartHome, eventProcessor);

        hallDoorClosedTest.closeHallDoor();
        hallDoorClosedTest.closeNotHallDoor();
    }

}
