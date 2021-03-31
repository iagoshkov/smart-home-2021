package ru.sbt.mipt.oop;

import java.util.Objects;

public record PanicAlarmSystemState(AlarmSystem alarmSystem,
                                    String code,
                                    Messenger messenger) implements AlarmSystemState {

    @Override
    public void deactivate(String code) {
        if (Objects.equals(this.code, code)) {
            alarmSystem.setState(new InactiveAlarmSystemState(alarmSystem));
        }
    }

    @Override
    public boolean allowSensorEvents() {
        messenger.sendMessage("Sending sms");
        return false;
    }

}
