package ru.sbt.mipt.oop;

public class OutputStreamImpl implements OutputStream {
    @Override
    public void sendCommand(SensorCommand command)  {
        System.out.println("Pretend we're sending command " + command);
    }
}
