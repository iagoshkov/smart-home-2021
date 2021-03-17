package ru.sbt.mipt.oop;

public class LightOffHandler implements Action {

    private final String lightId;

    public LightOffHandler(SensorEvent event) {
        if (event.getType() != SensorEventType.LIGHT_OFF) {
            throw new IllegalArgumentException("The event type must be LIGHT_OFF. Given: " + event.getType());
        }
        this.lightId = event.getObjectId();
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Room room) {
            room.execute(new TurnLightOnOffAction(lightId, false));
            System.out.println("Light " + lightId + " in room " + room.getName() + " was turned off.");
        }
    }

}
