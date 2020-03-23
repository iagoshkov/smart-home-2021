package ru.sbt.mipt.oop.signaling;

public class AlarmStatus implements Status {
    private final Signaling signaling;
    private final String code;

    public AlarmStatus(Signaling signaling, String code) {
        this.signaling = signaling;
        this.code = code;
    }

    @Override
    public void activateSignaling(String code) {
    }

    @Override
    public void deactivateSignaling(String code) {
        if (this.code.equals(code)) {
            signaling.setStatus(new DeactivatedStatus(signaling));
        }
    }

    @Override
    public void turnOnAlarm() {
    }
}
