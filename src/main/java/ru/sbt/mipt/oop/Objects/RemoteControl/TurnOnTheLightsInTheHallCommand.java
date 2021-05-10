package ru.sbt.mipt.oop.Objects.RemoteControl;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Event.LightEvent;
import ru.sbt.mipt.oop.Handler.LightEventHandler;
import ru.sbt.mipt.oop.Objects.HomeObjects.HomeObject;
import ru.sbt.mipt.oop.Objects.HomeObjects.Light;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.Room;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.SmartHome;
import ru.sbt.mipt.oop.Type.EventType;
import static ru.sbt.mipt.oop.Command.LightCommands.turnTheLightOnCommand;

public class TurnOnTheLightsInTheHallCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnTheLightsInTheHallCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Action checkTheHallLights = (HomeObject homeObject) -> {
            if (homeObject instanceof Room && homeObject.getId().equals("hall")) {
                Action action = (HomeObject currentRoomObject) -> {
                    if (currentRoomObject instanceof Light) {
                        turnTheLightOnCommand((Light) currentRoomObject);
                        LightEvent event = new LightEvent(currentRoomObject.getId(), LightEventType.ON);
                        LightEventHandler handler = new LightEventHandler(smartHome);
                        handler.handleEvent(event);
                    }
                };
                ((Room) homeObject).execute(action);
            }
        };
        smartHome.execute(checkTheHallLights);
    }
}
