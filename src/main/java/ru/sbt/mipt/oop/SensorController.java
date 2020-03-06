package ru.sbt.mipt.oop;

interface SensorController {
    default void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
