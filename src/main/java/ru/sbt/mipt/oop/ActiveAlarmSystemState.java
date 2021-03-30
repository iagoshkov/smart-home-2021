package ru.sbt.mipt.oop;

public class ActiveAlarmSystemState extends AlarmSystemState {

    private final String code;

    protected ActiveAlarmSystemState(AlarmSystem alarmSystem, String code) {
        super(alarmSystem);
        this.code = code;
    }

    @Override
    public void deactivate(String code) {
        if (this.code == null || this.code.equals(code)) {
            alarmSystem.setState(new InactiveAlarmSystemState(alarmSystem));
        } else {
            alarmSystem.setState(new PanicAlarmSystemState(alarmSystem, this.code, System.out::println));
        }
    }

}
