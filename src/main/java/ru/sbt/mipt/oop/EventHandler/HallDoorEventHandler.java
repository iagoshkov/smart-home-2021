package ru.sbt.mipt.oop.EventHandler;

import ru.sbt.mipt.oop.CommandSender;
import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome.*;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public HallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        System.out.println("Turning lights off at whole home");
        if(event.getType().equals(DOOR_CLOSED)) {
            Action turnAllLightsOff = (object) -> {
                if (object instanceof Light) {
                    ((Light)object).setOn(false);
                }
            };
            Action turnAllLightsOffIfRoomIsHall = (object) -> {
                if (object instanceof Room && ((Room)object).getName().equals("hall")) {
                    Room room = (Room)object;
                    room.execute(roomObj -> {
                        if(roomObj instanceof Door && ((Door) roomObj).getId().equals(event.getObjectId())) {
                            smartHome.execute(turnAllLightsOff);
                        }
                    });
                }
            };
        }
    }
}
