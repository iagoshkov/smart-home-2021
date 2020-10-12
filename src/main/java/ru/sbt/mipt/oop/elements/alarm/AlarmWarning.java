package ru.sbt.mipt.oop.elements.alarm;

public class AlarmWarning implements AlarmBehavior {
    private final AlarmSystem system;

    AlarmWarning(AlarmSystem system) {
        this.system = system;
    }
    @Override
    public void activate(Object activationHashCode, Object providedHashCode) {
        if (activationHashCode.equals(providedHashCode)) {
            System.out.println("Alarm activated, end of warning");
            system.setAlarmBehavior(AlarmState.ACTIVATED);
        } else {
            System.out.println("Invalid activation code");
        }
    }

    @Override
    public void deactivate(Object activationHashCode, Object providedHashCode) {
        if (activationHashCode.equals(providedHashCode)) {
            System.out.println("Alarm deactivated, end of warning");
            system.setAlarmBehavior(AlarmState.DEACTIVATED);
        } else {
            System.out.println("Invalid deactivation code");
        }
    }

    @Override
    public void warn() {
        System.out.println("Alarm is already in warning mode");
    }


    @Override
    public AlarmState getState() {
        return AlarmState.WARNING;
    }
}
