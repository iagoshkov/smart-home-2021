package ru.sbt.mipt.oop.signaling;

public class Signaling {
    private Status status = new DeactivatedStatus(this);
    private String code = "";

    public String getCode() {
        return code;
    }

    public Status getStatus() {
        return status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void activateSignaling(String code) throws ActivationException {
        status.activateSignaling(code);
    }

    public void deactivateSignaling(String code) {
        status.deactivateSignaling(code);
    }

    public void turnOnAlarm() {
        status.turnOnAlarm();
    }
}
