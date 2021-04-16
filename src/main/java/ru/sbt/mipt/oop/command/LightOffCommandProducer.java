package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;

public class LightOffCommandProducer implements CommandProducer {

    @Override
    public void produceCommand(SmartHome smartHome) {
        CommandSender commandSender = command -> System.out.println("Pretend we're sending command " + command);

        smartHome.execute(obj -> {
            if (obj instanceof Light light) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandSender.sendCommand(command);
            }
        });
    }

}
