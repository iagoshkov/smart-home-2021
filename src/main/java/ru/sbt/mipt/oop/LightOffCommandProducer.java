package ru.sbt.mipt.oop;

public class LightOffCommandProducer implements CommandProducer {

    @Override
    public void produceCommand(SmartHome smartHome) {
        smartHome.execute(new LightOffAction());

        CommandSender commandSender = new LightOffCommandSender();

        AllLightsAction allLightAction = new AllLightsAction();
        smartHome.execute(allLightAction);

        for (Light light: allLightAction.getLights()) {
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            commandSender.sendCommand(command);
        }
    }

}
