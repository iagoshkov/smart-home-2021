package ru.sbt.mipt.oop.smarthome.remotecontrol;

import ru.sbt.mipt.oop.smarthome.remotecontrol.command.Command;
import ru.sbt.mipt.oop.smarthome.remotecontrol.command.DummyCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RemoteController implements rc.RemoteControl {
    private Map<String, Command> commandMap = makeCommandMap();
    private static final Set<String> buttonCodes = Set.of("A", "B", "C", "D", "1", "2", "3", "4");

    public void setCommand(String buttonCode, Command command) {
        if (!buttonCodes.contains(buttonCode))
            return;

        commandMap.put(buttonCode, command);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        commandMap.get(buttonCode).execute();
    }

    private Map<String, Command> makeCommandMap() {
        Map<String, Command> map = new HashMap<>();
        for (String buttonCode : buttonCodes) {
            map.put(buttonCode, new DummyCommand());
        }
        return map;
    }
}
