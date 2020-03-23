package ru.sbt.mipt.oop.signaling;

public class Signaling {
    private Status status = new DeactivatedStatus(this);

    public Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }

    public void activateSignaling(String code) {
        status.activateSignaling(code);
    }

    public void deactivateSignaling(String code) {
        status.deactivateSignaling(code);
    }

    public void turnOnAlarm() {
        status.turnOnAlarm();
    }
}
