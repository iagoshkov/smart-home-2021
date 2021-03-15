package ru.sbt.mipt.oop;

public class LightOffCommandProducer implements CommandProducer {

    @Override
    public void produceCommand(SmartHome smartHome) {
        smartHome.execute(new LightOffAction());

        CommandSender commandSender = new LightOffCommandSender();

        for (LightSmartHomeIterator it = new LightSmartHomeIterator(smartHome); it.hasNext(); ) {
            Light light = it.next();

            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            commandSender.sendCommand(command);
        }
    }

}
