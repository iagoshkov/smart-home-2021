package ru.sbt.mipt.oop;


// interface HomeSupervision is used to separate sensor events
// logic (receiving events from sensors)
public interface HomeSupervision {
    SensorEvent getNextSensorEvent();
}
