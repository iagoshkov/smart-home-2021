package ru.sbt.mipt.oop.Objects.RemoteControl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;
import static org.junit.jupiter.api.Assertions.*;

class AlertAlarmCommandTest {
    @Mock
    SmartAlarm alarm = Mockito.mock(SmartAlarm.class);

    @InjectMocks
    AlertAlarmCommand alertAlarmCommand = new AlertAlarmCommand(alarm);

    @Test
    void executeWithoutDefiningAlarm() {
        Mockito.doAnswer((Answer<Void>) invocation -> null).when(alarm).alert();
        alertAlarmCommand.execute();
        Mockito.verify(alarm, Mockito.times(1)).alert();
    }

    @Test
    void executeWithActivatedAlarm() {
        alarm = new SmartAlarm();
        alarm.activate("code");
        alertAlarmCommand = new AlertAlarmCommand(alarm);

        alertAlarmCommand.execute();
        assertTrue(alarm.isAlert());
    }

    @Test
    void executeWithDeactivatedAlarm() {
        alarm = new SmartAlarm();
        alertAlarmCommand = new AlertAlarmCommand(alarm);

        alertAlarmCommand.execute();
        assertTrue(alarm.isDeactivated());
    }
}
