package ru.sbt.mipt.oop.Commands;

import ru.sbt.mipt.oop.Sender.SensorCommandSender;
import ru.sbt.mipt.oop.Objects.HomeObjects.Light;
import ru.sbt.mipt.oop.Type.CommandType;

public class LightCommands {
    public static void turnTheLightOffCommand(Light light) {
        light.setOn(false);
        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
        new SensorCommandSender(command).Send();
    }
}
