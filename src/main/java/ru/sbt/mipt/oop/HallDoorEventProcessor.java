package ru.sbt.mipt.oop;

public record HallDoorEventProcessor(CommandProducer commandProducer) implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) return;

        String hallRoomName = "hall";
        String doorId = event.getObjectId();

        smartHome.execute(obj -> {
            if (obj instanceof Room room) {
                if (room.getName().equals(hallRoomName)) { // find out the hall room
                    room.execute(element -> {
                        if (element instanceof Door door) {
                            if (door.getId().equals(doorId)) { // find out the closed door (if it is in hall room)
                                commandProducer.produceCommand(smartHome);
                            }
                        }
                    });
                }
            }
        });
    }

}
