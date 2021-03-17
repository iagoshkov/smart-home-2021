package ru.sbt.mipt.oop;

public class HallDoorEventProcessor implements EventProcessor {

    private final CommandProducer commandProducer;

    public HallDoorEventProcessor(CommandProducer commandProducer) {
        this.commandProducer = commandProducer;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) return;

        String hallRoomName = "hall";

        HallRoomDoorsAction hallRoomDoorsAction = new HallRoomDoorsAction(hallRoomName);
        smartHome.execute(hallRoomDoorsAction);

        for (Door door : hallRoomDoorsAction.getDoors()) {
            if (door.getId().equals(event.getObjectId())) {
                commandProducer.produceCommand(smartHome);
                break;
            }
        }
    }

}
