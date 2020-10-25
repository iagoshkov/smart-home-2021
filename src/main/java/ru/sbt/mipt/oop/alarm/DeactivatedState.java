package ru.sbt.mipt.oop.alarm;

public class DeactivatedState implements AlarmStateInterface {

    Alarm alarm;

    public DeactivatedState(Alarm alarm) { this.alarm = alarm; }

    @Override
    public void activate(int code) {
        System.out.println("Smart Alarm is on!");
        alarm.changeState(new ActivateState(alarm));
    }

    @Override
    public void deactivate(int code) {}

    @Override
    public void alarm() {}
}