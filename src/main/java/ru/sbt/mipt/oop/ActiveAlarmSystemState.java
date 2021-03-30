package ru.sbt.mipt.oop;

public record ActiveAlarmSystemState(AlarmSystem alarmSystem,
                                     String code) implements AlarmSystemState {
    @Override
    public void deactivate(String code) {
        if (this.code == null || this.code.equals(code)) {
            alarmSystem.setState(new InactiveAlarmSystemState(alarmSystem));
        } else {
            alarmSystem.setState(new PanicAlarmSystemState(alarmSystem, this.code, System.out::println));
        }
    }

    @Override
    public boolean allowSensorEvents() {
        alarmSystem.setState(new PanicAlarmSystemState(alarmSystem, this.code, System.out::println));
        return false;
    }

}
