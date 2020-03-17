package ru.sbt.mipt.oop.eventhandler;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.command.ProvisionalSensorCommandSender;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class EventHallDoorHandlerTest {

    @Test
    void handleEventCloseHallDoorTest() {
        Room hall = new Room("hall");
        hall.addLights(Arrays.asList(new Light("7", true), new Light("8", true), new Light("9", true)));
        hall.addDoors(Collections.singletonList(new Door(true, "4")));
        SmartHome smartHome = new SmartHome(Collections.singletonList(hall));
        String objectId = "4";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, objectId);
        EventHallDoorHandler hallDoorHandler = new EventHallDoorHandler(smartHome, new ProvisionalSensorCommandSender());
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(objectId)) {
                assertTrue(((Door) doorCandidate).isOpen());
            }
        });
        hallDoorHandler.handleEvent(event);
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(objectId)) {
                assertFalse(((Door) doorCandidate).isOpen());
            }
        });
        smartHome.execute(light -> {
            if (light instanceof Light) {
                assertFalse(((Light)light).isOn());
            }
        });
    }

    @Test
    void handleEventCloseNotHallDoorTest() {
        Room hall = new Room("room");
        hall.addLights(Arrays.asList(new Light("7", true), new Light("8", true), new Light("9", true)));
        hall.addDoors(Collections.singletonList(new Door(true, "4")));
        SmartHome smartHome = new SmartHome(Collections.singletonList(hall));
        String objectId = "4";
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, objectId);
        EventHallDoorHandler hallDoorHandler = new EventHallDoorHandler(smartHome, new ProvisionalSensorCommandSender());
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(objectId)) {
                assertTrue(((Door) doorCandidate).isOpen());
            }
        });
        hallDoorHandler.handleEvent(event);
        smartHome.execute(doorCandidate -> {
            if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(objectId)) {
                assertTrue(((Door) doorCandidate).isOpen());
            }
        });
        smartHome.execute(light -> {
            if (light instanceof Light) {
                assertTrue(((Light)light).isOn());
            }
        });
    }
}