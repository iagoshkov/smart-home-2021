package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;


public class EventLightHandler implements EventHandler{
    private final SmartHome smartHome;

    public EventLightHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private void handleLightOffEvent(SensorEvent event) {
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(lightCandidate -> {
                    if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals(event.getObjectId())) {
                        ((Light) lightCandidate).setOn(false);
                        System.out.println("Light " + ((Light) lightCandidate).getId() + " in room " + ((Room) roomCandidate).getName() + " was turned off.");                    }
                });
            }
        });
    }

    private void handleLightOnEvent(SensorEvent event) {
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(lightCandidate -> {
                    if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals(event.getObjectId())) {
                        ((Light) lightCandidate).setOn(true);
                        System.out.println("Light " + ((Light) lightCandidate).getId() + " in room " + ((Room) roomCandidate).getName() + " was turned on.");                    }
                });
            }
        });
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_OFF) {
            handleLightOffEvent(event);
        } else if (event.getType() == SensorEventType.LIGHT_ON) {
            handleLightOnEvent(event);
        }
    }
}
