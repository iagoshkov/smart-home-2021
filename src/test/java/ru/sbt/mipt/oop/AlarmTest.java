package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.actions.AllDoorsAction;
import ru.sbt.mipt.oop.actions.AllLightsAction;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmEvent;
import ru.sbt.mipt.oop.alarm.AlarmEventProcessor;
import ru.sbt.mipt.oop.alarm.AlarmEventType;
import ru.sbt.mipt.oop.event.handler.AlarmEventHandlerDecorator;
import ru.sbt.mipt.oop.util.SmartHomeTestComponent;

import java.util.List;
import java.util.stream.Collectors;

public class AlarmTest extends SmartHomeTestComponent {

    private final String correctCode = "xkcd";
    private final SmartHomeTest smartHomeTest;

    public AlarmTest() {
        super();

        SmartHomeTest smartHomeTest = new SmartHomeTest();
        smartHome = smartHomeTest.smartHome;
        eventHandler = smartHomeTest.eventHandler;

        String code = "xkcd";

        eventHandler = new AlarmEventHandlerDecorator(new Alarm(code), new AlarmEventProcessor(), eventHandler);

        this.smartHomeTest = new SmartHomeTest();
        smartHomeTest.set(this.smartHome, eventHandler);
    }

    @Test
    public void runSmartHomeTests() {
        smartHomeTest.doorEvents();
        smartHomeTest.lightEvents();
        smartHomeTest.hallDoorClosed();
    }

    @Test
    public void correctCode() {
        smartHomeTest.eventHandler.handleEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, correctCode));

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
    public void doWithoutCode() {
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
    public void incorrectCode() {
        smartHomeTest.eventHandler.handleEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "123"));

        doWithoutCode();
    }

    @Test
    public void correctCodeAfterIncorrectTest() {
        smartHomeTest.eventHandler.handleEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "123"));

        doWithoutCode();

        smartHomeTest.eventHandler.handleEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, correctCode));

        runSmartHomeTests();
    }

    @Test
    public void deactivateAndActivate() {
        String code1 = correctCode;

        smartHomeTest.eventHandler.handleEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, code1));

        runSmartHomeTests();

        String code2 = correctCode + correctCode;

        smartHomeTest.eventHandler.handleEvent(new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, code2));

        doWithoutCode();

        smartHomeTest.eventHandler.handleEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, code1));

        doWithoutCode();

        smartHomeTest.eventHandler.handleEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, code2));

        runSmartHomeTests();
    }

}
