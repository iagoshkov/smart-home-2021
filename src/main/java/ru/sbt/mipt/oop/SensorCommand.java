package ru.sbt.mipt.oop;

public record SensorCommand(CommandType type, String objectId) {

    @Override
    public String toString() {
        return "SensorCommand{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }

}
