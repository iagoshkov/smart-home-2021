package ru.sbt.mipt.oop;

public class CommandProducer {
    private final SmartHome smartHome;

    public CommandProducer(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    void produceCommand(CommandType commandType) {
        switch (commandType) {
            case LIGHT_OFF:
                for (Room homeRoom : smartHome.getRooms()) {
                    for (Light light : homeRoom.getLights()) {
                        light.setOn(false);
                        SensorCommand command = new SensorCommand(commandType, light.getId());
                        sendCommand(command);
                    }
                }
            default:
                // do nothing
        }
    }

    private void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
