package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.events.processors.Action;

public class Alarm implements Actionable {
    private enum AlarmStates{
        ACTIVATED,
        DEACTIVATED,
        ALERT
    }

    private final String id;
    private String password;
    private AlarmStates state = AlarmStates.DEACTIVATED;

    public Alarm(String id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException();
        this.id = id;
    }

    public boolean activate(String password) {
        if (state == AlarmStates.ACTIVATED) return false;
        if (password == null || password.trim().isEmpty()) return false;
        if (this.password != null && !this.password.equals(password)) return false;

        if (this.password == null)
            this.password = password;

        state = AlarmStates.ACTIVATED;
        return true;
    }

    public boolean deactivate(String password) {
        if (state == AlarmStates.DEACTIVATED) return false;
        if (password == null || password.trim().isEmpty()) return false;
        if (!this.password.equals(password)) {
            activateAlert();
            return false;
        }
        state = AlarmStates.DEACTIVATED;
        return true;
    }

    public boolean activateAlert() {
        if (state != AlarmStates.ACTIVATED) return false;
        System.out.println("Alarm! Sending sms");
        state = AlarmStates.ALERT;
        return true;
    }

    public boolean isAlert() {
        return state == AlarmStates.ALERT;
    }

    public boolean isActivated() {
        return state == AlarmStates.ALERT || state == AlarmStates.ACTIVATED;
    }

    public String getId() {
        return id;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
