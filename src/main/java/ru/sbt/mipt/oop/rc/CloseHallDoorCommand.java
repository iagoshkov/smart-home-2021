package ru.sbt.mipt.oop.rc;

import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class CloseHallDoorCommand implements Command {
    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    @Override
    public void execute() {
        smartHome.execute(room -> {
            if (room instanceof Room) {
                ((Room) room).execute(door -> {
                    if (door instanceof Door && ((Room) room).getName().equals("hall")) {
                        ((Door) door).setOpen(false);
                    }
                });
            }
        });
    }
}
