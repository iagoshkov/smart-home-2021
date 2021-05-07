package ru.sbt.mipt.oop.Commands;
import ru.sbt.mipt.oop.Type.CommandType;

public class SensorCommands {
    private CommandType type;
    private String objectId;

    public SensorCommand(CommandType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "SensorCommand{" +
                "type=" + type + ", objectId='" + objectId + "\'" + "}";
    }
}
