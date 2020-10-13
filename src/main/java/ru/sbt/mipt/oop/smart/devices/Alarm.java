package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.events.processors.Action;

public class Alarm implements Actionable {
    private enum AlarmStates{
        ACTIVATED,
        DEACTIVATED,
        ALERT
    }

    private final String id;
    private final String password;
    private AlarmStates state = AlarmStates.DEACTIVATED;

    public Alarm(String id, String password) throws IllegalArgumentException {
        if (id == null || password == null) throw new IllegalArgumentException();
        this.id = id;
        this.password = password;
    }

    public boolean activate(String password) {
        if (state == AlarmStates.ACTIVATED) return false;
        if (!this.password.equals(password)) return false;
        state = AlarmStates.ACTIVATED;
        System.out.println("Alarm activated");
        return true;
    }

    public boolean deactivate(String password) {
        if (state == AlarmStates.DEACTIVATED) return false;
        if (!this.password.equals(password)) {
            activateAlert();
            return false;
        }
        state = AlarmStates.DEACTIVATED;
        System.out.println("Alarm deactivated");
        return true;
    }

    public void activateAlert() {
        if (state == AlarmStates.ACTIVATED) {
            state = AlarmStates.ALERT;
            System.out.println("Alarm alert!!!");
        }
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
