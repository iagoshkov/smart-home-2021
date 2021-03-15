package ru.sbt.mipt.oop;

public class DoorClosedCommandGenerator {

    public static CommandType generateCommand(Room room) {
        String hallRoomName = "hall";
        if (room.getName().equals(hallRoomName)) {
            return CommandType.LIGHT_OFF;
        }
        return null;
    }

}
