package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventTypeLight.*;
import static ru.sbt.mipt.oop.SensorEventTypeDoor.*;

public class Event {
    private static SensorEvent event;

    private static SensorEvent randomEvent(){
        Double randomNumber = Math.random();
        if (randomNumber < 0.05)
            return null; // null means end of event stream
        SensorEventTypeLight  sensorEventTypeLight = null;
        SensorEventTypeDoor sensorEventTypeDoor = null;

        if(4 * randomNumber % 2 == 0)
            sensorEventTypeLight =  SensorEventTypeLight.values()[(int) (4 * randomNumber) % 2];
        else
            sensorEventTypeDoor = SensorEventTypeDoor.values()[(int) (4 * randomNumber) % 2];

        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventTypeDoor, sensorEventTypeLight, objectId);
    }
    public Event (){
        this.event = randomEvent();
    }
    public Event(SensorEvent _event){
        this.event = _event;
    }

    public void next() {
        this.event =  randomEvent();
    }

    public SensorEvent getEvent(){
        return this.event;
    }

    public void print(SmartHome smartHome){
        System.out.println("Got event: " + event);
        if (event.getTypeLight() == LIGHT_ON || event.getTypeLight() == LIGHT_OFF){
            // событие от источника света
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        LightInformation.printLight(this, light, room);
                    }
                }
            }
        }
        if (event.getTypeDoor() == DOOR_OPEN || event.getTypeDoor() == DOOR_CLOSED) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        DoorInformation.printDoor(this, door, smartHome, room);
                    }
                }
            }
        }
    }
}
