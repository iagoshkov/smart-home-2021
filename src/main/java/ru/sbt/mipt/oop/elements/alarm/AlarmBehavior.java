package ru.sbt.mipt.oop.elements.alarm;

public interface AlarmBehavior {
    void activate(Object activationHashCode, Object providedHashCode);
    void deactivate(Object activationHashCode, Object providedHashCode);
    void warn();
    AlarmState getState();
}
