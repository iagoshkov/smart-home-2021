package ru.sbt.mipt.oop.Handler;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Event.LightEvent;
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Objects.HomeObjects.HomeObject;
import ru.sbt.mipt.oop.Objects.HomeObjects.Light;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.SmartHome;
import static ru.sbt.mipt.oop.Type.EventType.ON;

public class LightEventHandler implements SensorEventHandler {
    private SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
  
    private void turnTheLightOff(Light light) {
        light.setOn(false);
        System.out.println("Light " + light.getId()  + " was turned off.");
    }

    private void turnTheLightOn(Light light) {
        light.setOn(true); 
        System.out.println("Light " + light.getId() + " was turned on.");
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event instanceof LightEvent) {
            Action action = (HomeObject homeObject) -> {
                if (homeObject instanceof Light && homeObject.getId().equals(event.getObjectId())) {
                    if (((LightEvent) event).getLightEventType() == ON) {
                        turnTheLightOn((Light) homeObject);
                    } else {
                        turnTheLightOff((Light) homeObject);
                    }
                }
            };
            smartHome.execute(action);
        }
    }
}
