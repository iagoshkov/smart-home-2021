package ru.sbt.mipt.oop.smart.actions;

import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceType;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public class ActionTurnOffAllLightsInSmartHome implements Action {
    @Override
    public void act(SmartDevice smartDevice) {
        if (smartDevice == null || smartDevice.getType() != SmartDeviceType.SMART_HOME) return;
        SmartHome smartHome = (SmartHome) smartDevice;

        smartHome.getAllDevices()
                .stream()
                .filter(d -> d.getType().equals(SmartDeviceType.LIGHT))
                .forEach(d -> (new ActionLightOff()).act(d));

        System.out.println("All lights in Smart Home were off");
    }
}
