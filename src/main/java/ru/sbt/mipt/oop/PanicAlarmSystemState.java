package ru.sbt.mipt.oop;

public record PanicAlarmSystemState(AlarmSystem alarmSystem, String code,
                                    Messenger messenger) implements AlarmSystemState {

    @Override
    public void deactivate(String code) {
        if (this.code.equals(code)) {
            alarmSystem.setState(new InactiveAlarmSystemState(alarmSystem));
        }
    }

    @Override
    public boolean allowSensorEvents() {
        messenger.sendMessage("Sending sms");
        return false;
    }

}
