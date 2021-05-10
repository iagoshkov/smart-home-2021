package ru.sbt.mipt.oop.Objects.RemoteControl;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Event.DoorEvent;
import ru.sbt.mipt.oop.Handler.HallDoorEventHandler;
import ru.sbt.mipt.oop.Objects.HomeObjects.Door;
import ru.sbt.mipt.oop.Objects.HomeObjects.HomeObject;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.Room;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.SmartHome;
import ru.sbt.mipt.oop.Type.EventType;
import static ru.sbt.mipt.oop.commands.DoorCommands.closeTheDoorCommand;

public class CloseTheHallDoorCommand implements Command {
    private final SmartHome smartHome;

    public CloseTheHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Action checkTheHallDoor = (HomeObject homeObject) -> {
            if (homeObject instanceof Room && homeObject.getId().equals("hall")) {
                Action action = (HomeObject currentRoomObject) -> {
                    if (currentRoomObject instanceof Door) {
                        closeTheDoorCommand((Door) currentRoomObject);
                        DoorEvent event = new DoorEvent(currentRoomObject.getId(), DoorEventType.OFF);
                        HallDoorEventHandler handler = new HallDoorEventHandler(smartHome);
                        handler.handleEvent(event);
                    }
                };
                ((Room) homeObject).execute(action);
            }
        };
        smartHome.execute(checkTheHallDoor);
    }
}
