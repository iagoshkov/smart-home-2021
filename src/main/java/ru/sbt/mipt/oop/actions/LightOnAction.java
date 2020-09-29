package ru.sbt.mipt.oop.actions;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.Room;
import ru.sbt.mipt.oop.devices.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;

public class LightOnAction implements Action {
    private final SmartHome smartHome;
    private final SensorEvent event;

    public LightOnAction(SmartHome smartHome, SensorEvent event) {
        this.smartHome = smartHome;
        this.event = event;
    }

    public void execute() {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    light.turnOn();
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                }
            }
        }
    }
}
