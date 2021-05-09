package ru.sbt.mipt.oop;

//import ru.sbt.mipt.oop.*;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        // событие от источника света
        //System.out.println("Handling light event");
        smartHome.execute((object) -> {
            if(object instanceof Light) {
                //System.out.println("A");
                Light light = (Light) object;
                if(light.getId().equals(event.getObjectId())) {
                    if(event.getType().equals(LIGHT_ON)) {
                        light.setOn(true);
                        System.out.println("light " + light.getId() + " was set ON");
                    }
                    if(event.getType().equals(LIGHT_OFF)) {
                        light.setOn(false);
                        System.out.println("light " + light.getId() + " was set OFF");
                    }
                }
            }
        });
        /*for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }*/
    }
}
