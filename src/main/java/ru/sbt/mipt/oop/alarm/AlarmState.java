package ru.sbt.mipt.oop.alarm;

public interface AlarmState {

    default void activate(String code) {
        // do nothing
    }

    default void deactivate(String code) {
        // do nothing
    }

    default void panic() {
        // do nothing
    }

    default boolean allowSensorEvents() {
        return true;
    }

}
