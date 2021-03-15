package ru.sbt.mipt.oop;

public class LightOffCommandProducer implements CommandProducer {

    @Override
    public void produceCommand(SmartHome smartHome, CommandType commandType) {
        if (commandType != CommandType.LIGHT_OFF) return;

        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(commandType, light.getId());
                sendCommand(command);
            }
        }
    }

    private void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

}
