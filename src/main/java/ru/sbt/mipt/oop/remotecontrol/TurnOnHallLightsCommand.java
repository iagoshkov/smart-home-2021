package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.Room;

public class TurnOnHallLightsCommand implements RemoteControlCommand {

    private final SmartHome smartHome;

    public TurnOnHallLightsCommand(SmartHome smartHome) {

        this.smartHome = smartHome;
    }

    @Override
    public void execute() {

        smartHome.execute(component ->{

            if(!(component instanceof Room))  {

                return;
            }
            Room room = (Room) component;

            if(room.getName().equals("hall")) {

                room.execute(roomComponent-> {

                    if (roomComponent instanceof Light) {
                        ((Light) roomComponent).setOn(true);
                    }
                });
            }
        });
    }
}

