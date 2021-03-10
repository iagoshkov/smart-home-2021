package ru.sbt.mipt.oop;

public class CommandSender {
    private final SmartHome smartHome;

    public CommandSender(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void sendAllLight() {
        for (Room room: smartHome.getRooms()) {
            for (Light light: room.getLights()) {
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
