package ru.sbt.mipt.oop.signaling;

public class ActivatedStatus extends Status {
    public ActivatedStatus(Signaling signaling) {
        super(signaling);
    }

    @Override
    void activateSignaling(String code) throws ActivationException {
        throw new ActivationException("The signaling is already active!");
    }

    @Override
    void deactivateSignaling(String code) {
        if (signaling.getCode().equals(code)) {
            signaling.setStatus(new DeactivatedStatus(signaling));
        } else {
            signaling.setStatus(new AlarmStatus(signaling));
        }
    }

    @Override
    void turnOnAlarm() {
        signaling.setStatus(new AlarmStatus(signaling));
    }
}
