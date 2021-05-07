package ru.sbt.mipt.oop.Generator.EventGenerator;
import ru.sbt.mipt.oop.Event.*;
import ru.sbt.mipt.oop.Type.AlarmEventType;

public class AlarmEventGenerator implements EventGenerator {
    @Override
    public SensorEvent Generate(String code) {
        AlarmEventType alarmEventType = AlarmEventType.values()[(int) (2 * Math.random())];
        return new AlarmEvent(alarmEventType, code);
    }
}
