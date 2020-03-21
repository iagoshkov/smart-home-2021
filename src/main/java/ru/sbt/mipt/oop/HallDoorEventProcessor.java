package ru.sbt.mipt.oop;

public class HallDoorEventProcessor implements Processor<Room>{
    SmartHome smartHome;
    HallDoorIterator hallDoorIterator;
    public HallDoorEventProcessor(SmartHome smartHome, HallDoorIterator hallDoorIterator){
        this.smartHome = smartHome;
        this.hallDoorIterator = hallDoorIterator;
    }
    @Override
    public void processing(Room room) {
        if (room.getName().equals("hall")) {
            hallDoorIterator.iterate(room);
        }
    }
}
