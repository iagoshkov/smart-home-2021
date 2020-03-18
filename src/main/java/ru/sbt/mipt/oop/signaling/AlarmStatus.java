package ru.sbt.mipt.oop.signaling;

public class AlarmStatus extends Status {
    AlarmStatus(Signaling signaling) {
        super(signaling);
    }

    @Override
    void activateSignaling(String code) {
    }

    @Override
    void deactivateSignaling(String code) {
        if (signaling.getCode().equals(code)) {
            signaling.setStatus(new DeactivatedStatus(signaling));
        }
    }

    @Override
    void turnOnAlarm() {
    }
}
