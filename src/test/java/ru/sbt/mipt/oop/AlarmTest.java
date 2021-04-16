package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.actions.AllDoorsAction;
import ru.sbt.mipt.oop.actions.AllLightsAction;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.event.AlarmEvent;
import ru.sbt.mipt.oop.event.handler.AlarmEventHandler;
import ru.sbt.mipt.oop.event.AlarmEventType;
import ru.sbt.mipt.oop.event.processor.AlarmEventProcessorDecorator;
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
        eventProcessor = smartHomeTest.eventProcessor;

        String code = "xkcd";

        eventProcessor = new AlarmEventProcessorDecorator(new Alarm(code), new AlarmEventHandler(), eventProcessor);

        this.smartHomeTest = new SmartHomeTest();
        smartHomeTest.set(this.smartHome, eventProcessor);
    }

    @Test
    public void runSmartHomeTests() {
        smartHomeTest.doorEvents();
        smartHomeTest.lightEvents();
        smartHomeTest.hallDoorClosed();
    }

    @Test
    public void correctCode() {
        smartHomeTest.eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, correctCode));

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

        SmartHomeSimulator.simulateWork(eventProcessor);

        List<Boolean> doorsAreOpenAfter = getAllDoorsAreOpen();
        List<Boolean> lightsAreOnAfter = getAllLightsAreOn();

        Assert.assertEquals(doorsAreOpenBefore.size(), doorsAreOpenAfter.size());
        Assert.assertArrayEquals(doorsAreOpenBefore.toArray(), doorsAreOpenAfter.toArray());

        Assert.assertEquals(lightsAreOnBefore.size(), lightsAreOnAfter.size());
        Assert.assertArrayEquals(lightsAreOnBefore.toArray(), lightsAreOnAfter.toArray());
    }

    @Test
    public void incorrectCode() {
        smartHomeTest.eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "123"));

        doWithoutCode();
    }

    @Test
    public void correctCodeAfterIncorrectTest() {
        smartHomeTest.eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "123"));

        doWithoutCode();

        smartHomeTest.eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, correctCode));

        runSmartHomeTests();
    }

    @Test
    public void deactivateAndActivate() {
        String code1 = correctCode;

        smartHomeTest.eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, code1));

        runSmartHomeTests();

        String code2 = correctCode + correctCode;

        smartHomeTest.eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, code2));

        doWithoutCode();

        smartHomeTest.eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, code1));

        doWithoutCode();

        smartHomeTest.eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, code2));

        runSmartHomeTests();
    }

}
