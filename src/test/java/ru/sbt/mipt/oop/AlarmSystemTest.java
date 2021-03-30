package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AlarmSystemTest extends SmartHomeTestComponent {

    private final String correctCode = "xkcd";
    private final SmartHomeTest smartHomeTest;

    public AlarmSystemTest() {
        super();

        SmartHomeTest smartHomeTest = new SmartHomeTest();
        smartHome = smartHomeTest.smartHome;
        eventHandler = smartHomeTest.eventHandler;

        String code = "xkcd";

        SmartHomeEventHandler eventHandlerWithAlarmSystem =
                new SmartHomeEventHandlerWithAlarmSystem(eventHandler, new AlarmSystemEventProcessor(), new AlarmSystem(code));

        this.smartHomeTest = new SmartHomeTest();
        smartHomeTest.set(this.smartHome, eventHandlerWithAlarmSystem);
    }

    @Test
    public void correctCodeTest() throws InvocationTargetException, IllegalAccessException {
        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, correctCode));

        ClassPublicMethodsTester.testClassPublicMethods(smartHomeTest);
    }

    private List<Door> getAllDoors() {
        AllDoorsAction allDoorsAction = new AllDoorsAction();
        smartHome.execute(allDoorsAction);
        return allDoorsAction.getDoors();
    }

    private List<Light> getAllLights() {
        AllLightsAction allLightsAction = new AllLightsAction();
        smartHome.execute(allLightsAction);
        return allLightsAction.getLights();
    }


    @Test
    public void doWithoutCodeTest() {
        List<Door> doorsBefore = getAllDoors();
        List<Light> lightsBefore = getAllLights();

        SmartHomeSimulator.simulateWork(eventHandler);

        List<Door> doorsAfter = getAllDoors();
        List<Light> lightsAfter = getAllLights();

        Assert.assertEquals(doorsBefore.size(), doorsAfter.size());
        for (int i = 0; i < doorsBefore.size(); ++i) {
            Assert.assertEquals(doorsBefore.get(i).isOpen(), doorsAfter.get(i).isOpen());
        }

        Assert.assertEquals(lightsBefore.size(), lightsAfter.size());
        for (int i = 0; i < lightsBefore.size(); ++i) {
            Assert.assertEquals(lightsBefore.get(i).isOn(), lightsAfter.get(i).isOn());
        }
    }

    @Test
    public void incorrectCodeTest() {
        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, "123"));

        doWithoutCodeTest();
    }

    @Test
    public void correctCodeAfterIncorrectTest() throws InvocationTargetException, IllegalAccessException {
        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, "123"));

        doWithoutCodeTest();

        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, correctCode));

        ClassPublicMethodsTester.testClassPublicMethods(smartHomeTest);
    }

    @Test
    public void deactivateAndActivateTest() throws InvocationTargetException, IllegalAccessException {
        String code1 = correctCode;

        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, code1));

        ClassPublicMethodsTester.testClassPublicMethods(smartHomeTest);

        String code2 = correctCode + correctCode;

        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_ACTIVATE, code2));

        doWithoutCodeTest();

        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, code1));

        doWithoutCodeTest();

        smartHomeTest.eventHandler.handleEvent(new AlarmSystemEvent(AlarmSystemEventType.ALARM_DEACTIVATE, code2));

        ClassPublicMethodsTester.testClassPublicMethods(smartHomeTest);
    }

}
