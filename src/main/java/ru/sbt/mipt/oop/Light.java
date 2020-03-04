package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class Light {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    void processEvent(SensorEvent event) {
        if (id.equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                doLightOn();
            } else if (event.getType() == LIGHT_OFF) {
                doLightOff();
            }
        }
    }

    private void doLightOn() {
        isOn = true;
        System.out.println("Light " + id + " was turned on.");
    }

    private void doLightOff() {
        isOn = false;
        System.out.println("Light " + id + " was turned off.");
    }

    void turnOffCommand() {
        isOn = false;
        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, id);
        Application.sendCommand(command);
    }
}
