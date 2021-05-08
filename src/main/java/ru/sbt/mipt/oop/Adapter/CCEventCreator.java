  
package ru.sbt.mipt.oop.Adapter;
import ru.sbt.mipt.oop.Event.SensorEvent;

public interface CCEventCreator {
    SensorEvent create(String id);
}

