package ru.sbt.mipt.oop.eventhandler;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.SmartHome;

import static org.junit.jupiter.api.Assertions.*;

class EventDoorHandlerTest {

    @Test
    void handleEventOpenDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "4";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, objectId);
        EventDoorHandler handler = new EventDoorHandler(smartHome);
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(objectId)) {
                assertFalse(((Door) doorCandidate).isOpen());
            }
        });
        handler.handleEvent(event);
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(objectId)) {
                assertTrue(((Door) doorCandidate).isOpen());
            }
        });
    }

    @Test
    void handleEventCloseDoorTest() {
        SmartHome smartHome = new HomeBuilder().getSmartHome();
        String objectId = "3";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, objectId);
        EventDoorHandler handler = new EventDoorHandler(smartHome);
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(objectId)) {
                assertTrue(((Door) doorCandidate).isOpen());
            }
        });
        handler.handleEvent(event);
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(objectId)) {
                assertFalse(((Door) doorCandidate).isOpen());
            }
        });
    }
}