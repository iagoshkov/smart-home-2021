package ru.sbt.mipt.oop.signaling;

public interface Status {
    void activateSignaling(String code);
    void deactivateSignaling(String code);
    void turnOnAlarm();
}
