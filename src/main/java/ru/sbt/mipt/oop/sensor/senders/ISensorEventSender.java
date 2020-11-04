package ru.sbt.mipt.oop.sensor.senders;

import ru.sbt.mipt.oop.SensorEvent;

public interface ISensorEventSender {
    SensorEvent getNextSensorEvent();
}
