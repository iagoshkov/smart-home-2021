package ru.sbt.mipt.oop;

public class ProvisionalSensorCommandSender implements SensorCommandSender {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
