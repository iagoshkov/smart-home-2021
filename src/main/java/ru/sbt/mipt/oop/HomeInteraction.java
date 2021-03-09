package ru.sbt.mipt.oop;


// interface HomeInteraction is used to separate home control
// and sensor events logic (or simply home interaction)
public interface HomeInteraction {

    void sendCommand(SensorCommand command);

    SensorEvent getNextSensorEvent();
}
