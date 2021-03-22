package ru.sbt.mipt.oop;

public class LightOffAction implements Action {

    private final String lightId;

    public LightOffAction(SensorEvent event) {
        this.lightId = event.getObjectId();
    }

    @Override
    public void apply(Object obj) {
        if (obj instanceof Room room) {
            room.execute(element -> {
                if (element instanceof Light light) {
                    if (light.getId().equals(lightId)) {
                        light.setOn(false);
                        System.out.println("Light " + lightId + " in room " + room.getName() + " was turned off.");
                    }
                }
            });
        }
    }

}
