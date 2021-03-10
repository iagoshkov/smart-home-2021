package ru.sbt.mipt.oop;

public class turnOfLightsScriptOfSmartHome implements scriptOfSmartHome {
    @Override
    public void execute(SmartHome smartHome, OutputStream output) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                output.sendCommand(command);
            }
        }
    }
}
