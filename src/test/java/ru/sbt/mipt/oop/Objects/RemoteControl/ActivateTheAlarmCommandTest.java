package ru.sbt.mipt.oop.Objects.RemoteControl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;
import static org.junit.jupiter.api.Assertions.*;

class ActivateTheAlarmCommandTest {
    @Mock
    SmartAlarm alarm = Mockito.mock(SmartAlarm.class);

    @InjectMocks
    ActivateTheAlarmCommand activateTheAlarmCommand = new ActivateTheAlarmCommand(alarm);

    @Test
    void executeWithoutDefiningAlarm() {
        Mockito.doAnswer((Answer<Void>) invocation -> null).when(alarm).activate(Mockito.anyString());
        activateTheAlarmCommand.execute();
        Mockito.verify(alarm, Mockito.times(1)).activate(Mockito.anyString());
    }

    @Test
    void executeTest() {
        alarm = new SmartAlarm();
        alarm.activate("code");
        alarm.alert();
        activateTheAlarmCommand = new ActivateTheAlarmCommand(alarm);

        activateTheAlarmCommand.execute();
        assertTrue(alarm.isAlert());
    }
}
