package ru.sbt.mipt.oop.Handler;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Event.DoorEvent;
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Objects.HomeObjects.*;
import ru.sbt.mipt.oop.Objects.HomeObjects.actionable.*;
import static ru.sbt.mipt.oop.Type.EventType.OFF;
import static ru.sbt.mipt.oop.Commands.LightCommands.turnTheLightOffCommand;

public class HallDoorEventHandler implements SensorEventHandler {
    private SmartHome smartHome;

    public HallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event instanceof DoorEvent && ((DoorEvent) event).getDoorEventType() == OFF) {
            Action checkTheHallDoor = (HomeObject homeObject) -> {
               if (homeObject instanceof Room && homeObject.getId().equals("hall")) {
                   Action action = (HomeObject currentRoomObject) -> {
                       if (currentRoomObject instanceof Door && currentRoomObject.getId().equals(event.getObjectId())) {
                           turnOffAllTheLights(); 
                       }
                   };
                   ((Room) homeObject).execute(action);
               }
            };
            smartHome.execute(checkTheHallDoor);
        }
    }

    private void turnOffAllTheLights() {
        Action action = (HomeObject homeObject) -> {
            if (homeObject instanceof Light) {
                turnTheLightOffCommand((Light) homeObject);
            }
        };
        smartHome.execute(action);
    }
}
