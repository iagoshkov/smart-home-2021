package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventTypeDoor.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventTypeDoor.DOOR_OPEN;
import static ru.sbt.mipt.oop.SensorEventTypeLight.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventTypeLight.LIGHT_ON;

public class PrintSmartHome implements PrintSmartHomeComponents<SmartHome, Event> {
    public void print(SmartHome smartHome, Event currentEvent){
        System.out.println("Got event: " + currentEvent.getEvent());
        if (currentEvent.getEvent().getTypeLight() == LIGHT_ON || currentEvent.getEvent().getTypeLight() == LIGHT_OFF){
            // событие от источника света
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(currentEvent.getEvent().getObjectId())) {
                        PrintLightInformation.printLight(currentEvent, light, room);
                    }
                }
            }
        }
        if (currentEvent.getEvent().getTypeDoor() == DOOR_OPEN || currentEvent.getEvent().getTypeDoor() == DOOR_CLOSED) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(currentEvent.getEvent().getObjectId())) {
                        DoorInformation doorInformation = new DoorInformation();
                        doorInformation.toggleDoorLight(currentEvent, door, smartHome, room);
                    }
                }
            }
        }
    }
}
