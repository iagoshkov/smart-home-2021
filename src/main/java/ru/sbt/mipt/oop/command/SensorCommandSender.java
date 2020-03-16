package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.command.SensorCommand;

public interface SensorCommandSender {
    public void sendCommand(SensorCommand command);
}
