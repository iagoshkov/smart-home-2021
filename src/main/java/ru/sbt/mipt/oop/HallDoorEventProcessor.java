package ru.sbt.mipt.oop;

public class HallDoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) return;

        String hallRoomName = "hall";

        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals(hallRoomName)) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        CommandProducer commandProducer = new LightOffCommandProducer();
                        commandProducer.produceCommand(smartHome);
                        return;
                    }
                }
            }
        }
    }

}
