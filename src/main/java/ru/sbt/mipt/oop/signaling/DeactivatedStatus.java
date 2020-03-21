package ru.sbt.mipt.oop.signaling;

public class DeactivatedStatus implements Status {
    private final Signaling signaling;

    public DeactivatedStatus(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void activateSignaling(String code) {
        signaling.setStatus(new ActivatedStatus(signaling, code));
    }

    @Override
    public void deactivateSignaling(String code) {
    }

    @Override
    public void turnOnAlarm() {
    }
}
