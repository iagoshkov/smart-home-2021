package ru.sbt.mipt.oop;

public class LightOnHandler implements Action {

    private final String lightId;

    public LightOnHandler(SensorEvent event) {
        if (event.getType() != SensorEventType.LIGHT_ON) {
            throw new IllegalArgumentException("The event type must be LIGHT_ON. Given: " + event.getType());
        }
        this.lightId = event.getObjectId();
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Room room) {
            room.execute(new TurnLightOnOffAction(lightId, true));
            System.out.println("Light " + lightId + " in room " + room.getName() + " was turned on.");
        }
    }

}
