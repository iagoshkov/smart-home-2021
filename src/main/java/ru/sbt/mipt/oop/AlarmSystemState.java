package ru.sbt.mipt.oop;

public interface AlarmSystemState {

    default void activate(String code) {
        // do nothing
    }

    default void deactivate(String code) {
        // do nothing
    }

    default boolean allowSensorEvents() {
        return true;
    }

    default void panic() {
        // do nothing
    }

}
