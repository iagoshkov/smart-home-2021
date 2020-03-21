package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventTypeDoor.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventTypeDoor.DOOR_OPEN;

public class DoorEventProcessor implements Processor<Event>{
    private SmartHome smartHome;
    private DoorIterator doorIterator;
    public DoorEventProcessor(SmartHome smartHome, DoorIterator doorIterator){
        this.smartHome = smartHome;
        this.doorIterator = doorIterator;
    }
    private boolean isDoor(Event event){
        return event.getEvent().getTypeDoor() == DOOR_OPEN || event.getEvent().getTypeDoor() == DOOR_CLOSED;
    }
    @Override
    public void processing(Event event) {
        if(isDoor(event)){
            // событие от двери
            doorIterator.iterate(event);
        }
    }
}
