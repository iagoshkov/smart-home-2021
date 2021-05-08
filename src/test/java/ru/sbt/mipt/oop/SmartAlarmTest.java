package ru.sbt.mipt.oop;
import org.junit.Test;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;

import static org.junit.jupiter.api.Assertions.*;

public class SmartAlarmTest {

    @Test
    public void activate() {
        SmartAlarm alarm = new SmartAlarm();
        alarm.activate("0");
        assertTrue(alarm.isActivated());
    }

    @Test
    public void deactivate() {
        SmartAlarm alarm = new SmartAlarm();
        alarm.activate("0");
        alarm.deactivate("0");
        assertTrue(alarm.isDeactivated());
    }

    @Test
    public void alert() {
        SmartAlarm alarm = new SmartAlarm();
        alarm.activate("0");
        alarm.alert();
        assertTrue(alarm.isAlert());
    }
}
