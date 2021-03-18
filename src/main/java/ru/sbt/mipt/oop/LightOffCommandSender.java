package ru.sbt.mipt.oop;

public class LightOffCommandSender implements CommandSender {

    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

}
