package ru.sbt.mipt.oop.Handler;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Event.DoorEvent;
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Objects.HomeObjects.Door;
import ru.sbt.mipt.oop.Objects.HomeObjects.HomeObject;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.SmartHome;
import static ru.sbt.mipt.oop.types.EventType.*;

public class DoorEventHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome; 
    }
    
    private void OpenTheDoor(Door door) {
        door.setOpen(true);
        System.out.println("Door " + door.getId()  + " was opened.");
    }
    
     private void CloseTheDoor(Door door) {
        door.setOpen(false);
        System.out.println("Door " + door.getId()  + " was closed.");
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event instanceof DoorEvent) {
            Action action = (HomeObject homeObject) -> {
                if (homeObject instanceof Door && homeObject.getId().equals(event.getObjectId())) {
                    if (((DoorEvent) event).getDoorEventType() == DOOR_OPEN) {
                        OpenTheDoor((Door) homeObject);
                    } else {
                        CloseTheDoor((Door) homeObject);
                    }
                }
            };
            smartHome.execute(action);
        }
    }   
}
