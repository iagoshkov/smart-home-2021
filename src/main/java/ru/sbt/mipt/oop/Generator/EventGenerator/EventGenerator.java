package ru.sbt.mipt.oop.Generator.EventGenerator;
import ru.sbt.mipt.oop.Event.*;

public interface EventGenerator {
    SensorEvent Generate(String objectId);
}
