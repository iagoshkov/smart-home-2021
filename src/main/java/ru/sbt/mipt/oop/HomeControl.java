package ru.sbt.mipt.oop;


// interface HomeControl is used to separate home control
// logic (command part of interaction with home)
public interface HomeControl {
    void sendCommand(SensorCommand command);
}
