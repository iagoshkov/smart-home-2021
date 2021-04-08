package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class CloseHallDoorCommand implements RemoteControlCommand {

    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {

        this.smartHome = smartHome;
    }

    @Override
    public void execute() {

        smartHome.execute(component -> {

            if (!(component instanceof Room)) {
                return;
            }

            Room room = (Room) component;

            if (room.getName().equals("hall")) {

                room.execute(hallComponent -> {

                    if (hallComponent instanceof Door) {

                        Door door = (Door) hallComponent;
                        door.setOpen(false);
                    }
                });
            }

        });
    }
}

