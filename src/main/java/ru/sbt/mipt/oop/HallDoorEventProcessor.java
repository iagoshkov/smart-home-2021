package ru.sbt.mipt.oop;

public class HallDoorEventProcessor implements Processor{
    SmartHome smartHome;
    HallDoorIterator hallDoorIterator;
    public HallDoorEventProcessor(SmartHome smartHome){
        this.smartHome = smartHome;
        this.hallDoorIterator = new HallDoorIterator(smartHome);
    }
    @Override
    public void processing(Event event) {

    }
    public void processing(Room room) {
        if (room.getName().equals("hall")) {
            hallDoorIterator.iterate(room);
        }
    }
}
