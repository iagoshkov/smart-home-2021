package ru.sbt.mipt.oop.alarm;

public class DeactivatedState implements AlarmStates {

    Alarm alarm;

    public DeactivatedState(Alarm alarm) { this.alarm = alarm; }

    @Override
    public void activate(int code) {

        if (code == alarm.getCode()) {
            System.out.println("Smart Alarm is on!");
            alarm.changeState(new ActivateState(alarm));
        } else {
            System.out.println("Incorrect code. Try again");
        }
    }

    @Override
    public void deactivate(int code) {}

    @Override
    public void alarm() {}
}