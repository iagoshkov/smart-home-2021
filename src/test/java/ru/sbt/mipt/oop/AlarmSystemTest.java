package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class AlarmSystemTest extends SmartHomeTestComponent {

    private final String correctCode = "xkcd";
    private final SmartHomeTest smartHomeTest;

    public AlarmSystemTest() {
        super();

        SmartHomeTest smartHomeTest = new SmartHomeTest();
        smartHome = smartHomeTest.smartHome;
        eventHandler = smartHomeTest.eventHandler;

        String code = "xkcd";

        eventHandler = new SmartHomeEventHandlerWithAlarmSystem(eventHandler, new AlarmSystemEventProcessor(), new AlarmSystem(code));

        this.smartHomeTest = new SmartHomeTest();
        smartHomeTest.set(this.smartHome, eventHandler);
    }

    @Test
    public void runSmartHomeTests() {
        smartHomeTest.doorEventsTest();
        smartHomeTest.lightEventsTest();
        smartHomeTest.hallDoorClosedTest();
    }

    @Test
    public void correctCodeTest() {
        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, correctCode));

        runSmartHomeTests();
    }

    private List<Door> getAllDoors() {
        AllDoorsAction allDoorsAction = new AllDoorsAction();
        smartHome.execute(allDoorsAction);
        return allDoorsAction.getDoors();
    }

    private List<Boolean> getAllDoorsAreOpen() {
        return getAllDoors().stream().map(Door::isOpen).collect(Collectors.toList());
    }

    private List<Light> getAllLights() {
        AllLightsAction allLightsAction = new AllLightsAction();
        smartHome.execute(allLightsAction);
        return allLightsAction.getLights();
    }

    private List<Boolean> getAllLightsAreOn() {
        return getAllLights().stream().map(Light::isOn).collect(Collectors.toList());
    }


    @Test
    public void doWithoutCodeTest() {
        List<Boolean> doorsAreOpenBefore = getAllDoorsAreOpen();
        List<Boolean> lightsAreOnBefore = getAllLightsAreOn();

        SmartHomeSimulator.simulateWork(eventHandler);

        List<Boolean> doorsAreOpenAfter = getAllDoorsAreOpen();
        List<Boolean> lightsAreOnAfter = getAllLightsAreOn();

        Assert.assertEquals(doorsAreOpenBefore.size(), doorsAreOpenAfter.size());
        Assert.assertArrayEquals(doorsAreOpenBefore.toArray(), doorsAreOpenAfter.toArray());

        Assert.assertEquals(lightsAreOnBefore.size(), lightsAreOnAfter.size());
        Assert.assertArrayEquals(lightsAreOnBefore.toArray(), lightsAreOnAfter.toArray());
    }

    @Test
    public void incorrectCodeTest() {
        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, "123"));

        doWithoutCodeTest();
    }

    @Test
    public void correctCodeAfterIncorrectTest() {
        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, "123"));

        doWithoutCodeTest();

        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, correctCode));

        runSmartHomeTests();
    }

    @Test
    public void deactivateAndActivateTest() {
        String code1 = correctCode;

        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, code1));

        runSmartHomeTests();

        String code2 = correctCode + correctCode;

        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_ACTIVATE, code2));

        doWithoutCodeTest();

        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, code1));

        doWithoutCodeTest();

        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, code2));

        runSmartHomeTests();
    }

}
