package ru.sbt.mipt.oop;


public class HomeControlSimulator implements HomeControl {

    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
