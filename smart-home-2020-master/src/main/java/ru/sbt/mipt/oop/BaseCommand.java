package ru.sbt.mipt.oop;

public class BaseCommand implements Command {
    @Override
    public void process(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
