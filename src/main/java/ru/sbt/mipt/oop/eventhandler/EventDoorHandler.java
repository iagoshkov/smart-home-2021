package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;


public class EventDoorHandler implements EventHandler {
    protected final SmartHome smartHome;

    public EventDoorHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_OPEN) {
            handleDoorOpenEvent(event);
        } else if (event.getType() == SensorEventType.DOOR_CLOSED ) {
            smartHome.execute(roomCandidate -> {
                if (roomCandidate instanceof Room) {
                    ((Room) roomCandidate).execute(doorCandidate -> {
                        if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(event.getObjectId()) && !((Room) roomCandidate).getName().equals("hall")) {
                            handleDoorClosedEvent(event);
                        }
                    });
                }
            });
        }
    }

    private void handleDoorOpenEvent(SensorEvent event) {
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(doorCandidate -> {
                    if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(event.getObjectId())) {
                        ((Door)doorCandidate).setOpen(true);
                        System.out.println("Door " + ((Door)doorCandidate).getId() + " in room " + ((Room) roomCandidate).getName() + " was opened.");
                    }
                });
            }
        });
    }

    protected void closeDoor(SensorEvent event) {
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(doorCandidate -> {
                    if (doorCandidate instanceof Door && ((Door) doorCandidate).getId().equals(event.getObjectId())) {
                        ((Door)doorCandidate).setOpen(false);
                        System.out.println("Door " + ((Door)doorCandidate).getId() + " in room " + ((Room) roomCandidate).getName() + " was closed.");
                    }
                });
            }
        });
    }

    private void handleDoorClosedEvent(SensorEvent event) {
        closeDoor(event);
    }
}
