package ru.sbt.mipt.oop.signaling;

public abstract class Status {
    protected final Signaling signaling;


    Status(Signaling signaling) {
        this.signaling = signaling;
    }

    abstract void activateSignaling(String code) throws ActivationException;
    abstract void deactivateSignaling(String code);
    abstract void turnOnAlarm();
}
