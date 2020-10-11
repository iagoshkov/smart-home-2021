package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.events.actions.Action;
import ru.sbt.mipt.oop.events.actions.ActionAlarmDeactivate;

public class Alarm implements SmartDevice, Actionable {
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

    public void activate(String password) {
        if (state == AlarmStates.ACTIVATED) return;
        if (!this.password.equals(password)) return;
        state = AlarmStates.ACTIVATED;
        System.out.println("Alarm activated");
    }

    public void deactivate(String password) {
        if (state == AlarmStates.DEACTIVATED) return;
        if (!this.password.equals(password)) {
            activateAlert();
            return;
        }
        state = AlarmStates.DEACTIVATED;
        System.out.println("Alarm deactivated");
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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void execute(Action action) {
        if (action == null) return;
        if (isActivated() && !(action instanceof ActionAlarmDeactivate)) {
            activateAlert();
            System.out.println("Sending sms");
        }
        action.act(this);
    }
}
