package ru.sbt.mipt.oop;

public class LightSensorController implements SensorController {
    private SmartHome smartHome;

    public LightSensorController(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void turnOffAllLights() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                turnOffLight(light);
            }
        }
    }

    private void turnOffLight(Light light) {
        light.setOn(false);
        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
        sendCommand(command);
    }
}
