package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.event.AlarmEvent;
import ru.sbt.mipt.oop.event.AlarmEventType;
import ru.sbt.mipt.oop.event.handler.AlarmEventHandler;
import ru.sbt.mipt.oop.event.processor.AlarmEventProcessorDecorator;
import ru.sbt.mipt.oop.event.processor.EventProcessor;

import java.util.List;

public class AlarmTest extends SmartHomeTestComponent {

    private final SmartHomeTest smartHomeTest;

    private final Alarm alarm;
    private final String correctCode = "xkcd";

    public AlarmTest() {
        super();

        smartHomeTest = new SmartHomeTest();

        alarm = new Alarm();
        EventProcessor eventProcessor = new AlarmEventProcessorDecorator(
                smartHomeTest.getEventProcessor(),
                alarm,
                new AlarmEventHandler()
        );

        set(smartHomeTest.getSmartHome(), eventProcessor);
        smartHomeTest.set(smartHome, eventProcessor);
    }

    @Before
    public void activateAlarm() {
        alarm.activate(correctCode);
    }

    @Test
    public void correctCode() {
        eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, correctCode));

        runSmartHomeTests();
    }

    @Test
    public void doWithoutCode() {
        List<Boolean> doorsAreOpenBefore = getDoorIsOpenList(doors);
        List<Boolean> lightsAreOnBefore = getLightIsOnList(lights);

        SmartHomeSimulator.simulateWork(eventProcessor);

        List<Boolean> doorsAreOpenAfter = getDoorIsOpenList(doors);
        List<Boolean> lightsAreOnAfter = getLightIsOnList(lights);

        Assert.assertEquals(doorsAreOpenBefore.size(), doorsAreOpenAfter.size());
        Assert.assertArrayEquals(doorsAreOpenBefore.toArray(), doorsAreOpenAfter.toArray());

        Assert.assertEquals(lightsAreOnBefore.size(), lightsAreOnAfter.size());
        Assert.assertArrayEquals(lightsAreOnBefore.toArray(), lightsAreOnAfter.toArray());
    }

    @Test
    public void incorrectCode() {
        eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "123"));

        doWithoutCode();
    }

    @Test
    public void correctCodeAfterIncorrectTest() {
        eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "123"));

        doWithoutCode();

        eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, correctCode));

        runSmartHomeTests();
    }

    @Test
    public void deactivateAndActivate() {
        String code1 = correctCode;

        eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, code1));

        runSmartHomeTests();

        String code2 = correctCode + correctCode;

        eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, code2));

        doWithoutCode();

        eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, code1));

        // like incorrect code
        doWithoutCode();

        eventProcessor.processEvent(new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, code2));

        runSmartHomeTests();
    }

    private void runSmartHomeTests() {
        smartHomeTest.doorEvents();
        smartHomeTest.lightEvents();
        smartHomeTest.hallDoorClosed();
    }

}
