package ru.sbt.mipt.oop.Objects.RemoteControl;
import java.util.Map;

public class UniqueRemoteControl {
    private final Map<String, Command> commandMap;

    public UniqueRemoteControl(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    public Command getCommandByButton(String buttonCode) {
        return commandMap.getOrDefault(buttonCode, null);
    }
}
