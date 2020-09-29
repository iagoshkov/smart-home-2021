package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.Room;
import ru.sbt.mipt.oop.devices.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;

public class TurnOffAllLightsAction implements Action {
    private final SmartHome smartHome;
    private final SensorEvent event;

    public TurnOffAllLightsAction(SmartHome smartHome, SensorEvent event) {
        this.smartHome = smartHome;
        this.event = event;
    }

    public void execute() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.turnOff();
            }
        }
    }
}
