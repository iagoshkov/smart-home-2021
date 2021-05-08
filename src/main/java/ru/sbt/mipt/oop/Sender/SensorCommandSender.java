package ru.sbt.mipt.oop.Sender;
import ru.sbt.mipt.oop.Command.SensorCommand;

public class SensorCommandSender {
    private SensorCommand command;
      
    public void Send() {
        System.out.println("Send " + this.command);
    }

    public SensorCommandSender(SensorCommand command) {
        this.command = command;
    }
}
