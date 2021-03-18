package ru.sbt.mipt.oop;

public class LightOffCommandProducer implements CommandProducer {

    @Override
    public void produceCommand(SmartHome smartHome, CommandType commandType) {
        if (commandType != CommandType.LIGHT_OFF) return;

        CommandSender commandSender = new LightOffCommandSender();

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(commandType, light.getId());
                commandSender.sendCommand(command);
            }
        }
    }

}
