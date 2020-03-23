package ru.sbt.mipt.oop.signaling;

public class ActivatedStatus implements Status{
    private final Signaling signaling;
    private final String code;

    public ActivatedStatus(Signaling signaling, String code) {
        this.signaling = signaling;
        this.code = code;
    }

    @Override
    public void activateSignaling(String code){
        System.out.println("The signaling was already activated!");
    }

    @Override
    public void deactivateSignaling(String code) {
        if (this.code.equals(code)) {
            signaling.setStatus(new DeactivatedStatus(signaling));
        } else {
            signaling.setStatus(new AlarmStatus(signaling, code));
        }
    }

    @Override
    public void turnOnAlarm() {
        signaling.setStatus(new AlarmStatus(signaling, code));
    }
}
