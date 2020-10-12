package ru.sbt.mipt.oop.elements.alarm;

public class AlarmDeactivated implements AlarmBehavior {
    private final AlarmSystem system;

    AlarmDeactivated(AlarmSystem system) {
        this.system = system;
    }

    @Override
    public void activate(Object activationHashCode, Object providedHashCode) {
        if (activationHashCode.equals(providedHashCode)) {
            System.out.println("Alarm is activated");
            system.setAlarmBehavior(AlarmState.ACTIVATED);
        } else {
            this.warn();
        }
    }

    @Override
    public void deactivate(Object activationHashCode, Object providedHashCode) {
        System.out.println("Alarm is already deactivated");
    }

    @Override
    public void warn() {
        System.out.println("Turning warning mode on!");
        system.setAlarmBehavior(AlarmState.WARNING);
    }

    @Override
    public AlarmState getState() {
        return AlarmState.DEACTIVATED;
    }
}
