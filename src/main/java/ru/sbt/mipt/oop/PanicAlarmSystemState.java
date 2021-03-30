package ru.sbt.mipt.oop;

public class PanicAlarmSystemState extends AlarmSystemState {

    private final String code;
    private final PanicMessenger panicMessenger;

    public PanicAlarmSystemState(AlarmSystem alarmSystem, String code) {
        super(alarmSystem);
        this.code = code;
        this.messenger = messenger;
    }

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
