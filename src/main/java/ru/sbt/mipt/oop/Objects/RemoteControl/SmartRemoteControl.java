package ru.sbt.mipt.oop.Objects.RemoteControl;
import rc.RemoteControl;
import java.util.Map;

public class SmartRemoteControl implements RemoteControl {
    private final Map<String, UniqueRemoteControl> uniqueRemoteControls;

    public SmartRemoteControl(Map<String, UniqueRemoteControl> uniqueRemoteControlMap) {
        this.uniqueRemoteControls = uniqueRemoteControlMap;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (uniqueRemoteControls.containsKey(rcId)) {
            UniqueRemoteControl remoteControl = uniqueRemoteControls.get(rcId);
            Command command = remoteControl.getCommandByButton(buttonCode);
            if (command != null) {
                command.execute();
            }
        }
    }
}
