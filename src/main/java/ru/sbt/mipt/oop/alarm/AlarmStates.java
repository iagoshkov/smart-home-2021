package ru.sbt.mipt.oop.alarm;

public interface AlarmStates {

    public abstract void activate(int code);
    public abstract void deactivate(int code);
    public abstract void alarm();
}
