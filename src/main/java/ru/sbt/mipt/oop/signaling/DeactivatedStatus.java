package ru.sbt.mipt.oop.signaling;

public class DeactivatedStatus extends Status {
    DeactivatedStatus(Signaling signaling) {
        super(signaling);
    }

    @Override
    void activateSignaling(String code) {
        signaling.setCode(code);
        signaling.setStatus(new ActivatedStatus(signaling));
    }

    @Override
    void deactivateSignaling(String code) {
    }

    @Override
    void turnOnAlarm() {
        signaling.setStatus(new AlarmStatus(signaling));
    }
}
