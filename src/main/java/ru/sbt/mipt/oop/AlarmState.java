package ru.sbt.mipt.oop;

public interface AlarmState {

    public void activate(String code);
    public void deactivate(String code);
    public void alert();
}
