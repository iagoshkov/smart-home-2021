package ru.sbt.mipt.oop;

import java.util.Objects;

public record AlarmPanicState(Alarm alarmSystem,
                              String code,
                              Messenger messenger) implements AlarmState {

    @Override
    public void deactivate(String code) {
        if (isCodeCorrect(code)) {
            alarmSystem.setState(new AlarmInactiveState(alarmSystem));
        }
    }

    @Override
    public boolean allowSensorEvents() {
        messenger.sendMessage("Sending sms");
        return false;
    }

    private boolean isCodeCorrect(String code) {
        return Objects.equals(this.code, code);
    }

}
