package ru.sbt.mipt.oop;

public class Alarm implements Actionable {
    private AlarmState state = new DeactivatedAlarmState(this);
    private String code;

    public void setState(AlarmState state) {
        this.state = state;
    }

    public AlarmState getState() {

        return state;
    }

    void setCode(String code) {

        this.code = code;
    }

    public void activate(String code) {

        state.activate(code);
    }

    public void deactivate(String code) {

        state.deactivate(code);
    }

    public boolean isDeactivated() {

        return (state instanceof  DeactivatedAlarmState);
    }


    public boolean isActivated() {

        return (state instanceof ActivatedAlarmState);
    }

    public boolean isAlert() {

        return (state instanceof AlertAlarmState);
    }
    public void alert() {

        state.alert();
    }

    public boolean codeIsCorrect(String code) {

        return code.equals(this.code);
    }

    @Override
    public void execute(Action action) {

        action.execute(this);
    }
}
