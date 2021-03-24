package ru.sbt.mipt.oop;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class SmartHomeTest {

    public SmartHome smartHome;
    public SmartHomeEventHandler eventHandler;

    public SmartHomeTest() {
        smartHome = new SomeSmartHomeCreator().create();

        List<EventProcessor> eventProcessors = Arrays.asList(
                new LightEventProcessor(),
                new DoorEventProcessor(),
                new HallDoorEventProcessor(new LightOffCommandProducer())
        );

        eventHandler = new SmartHomeSensorEventHandler(smartHome, eventProcessors);
    }

    private void testComponent(SmartHomeTestComponent component) throws IllegalAccessException, InvocationTargetException {
        component.smartHome = smartHome;
        component.eventHandler = eventHandler;

        ClassPublicMethodsTester.testClassPublicMethods(component);
    }

    @Test
    public void doorEventsTest() throws InvocationTargetException, IllegalAccessException {
        testComponent(new DoorEventsTest());
    }

    @Test
    public void lightEventsTest() throws InvocationTargetException, IllegalAccessException {
        testComponent(new LightEventsTest());
    }

    @Test
    public void hallDoorClosedTest() throws InvocationTargetException, IllegalAccessException {
        testComponent(new HallDoorClosedTest());
    }

}
