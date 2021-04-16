package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.Messenger;

import java.util.Objects;

public class AlarmPanicState implements AlarmState {

    final private Alarm alarm;
    final private String code;
    final private Messenger messenger;

    public AlarmPanicState(Alarm alarm, String code, Messenger messenger) {
        this.alarm = alarm;
        this.code = code;
        this.messenger = messenger;

        System.out.println("Alarm was got panic state");
    }

    @Override
    public void deactivate(String code) {
        if (isCodeCorrect(code)) {
            alarm.setState(new AlarmInactiveState(alarm));
        } else {
            sendMessage();
        }
    }

    @Override
    public boolean allowSensorEvents() {
        sendMessage();
        return false;
    }

    private boolean isCodeCorrect(String code) {
        return Objects.equals(this.code, code);
    }

    private void sendMessage() {
        messenger.sendMessage("Sending sms");
    }

}
