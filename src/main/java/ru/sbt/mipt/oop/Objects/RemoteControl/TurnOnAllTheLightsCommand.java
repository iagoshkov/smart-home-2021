package ru.sbt.mipt.oop.Objects.RemoteControl;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Event.LightEvent;
import ru.sbt.mipt.oop.Handler.LightEventHandler;
import ru.sbt.mipt.oop.Objects.HomeObjects.HomeObject;
import ru.sbt.mipt.oop.Objects.Homeobjects.Light;
import ru.sbt.mipt.oop.Objects.HomeObjects.Actionable.SmartHome;
import ru.sbt.mipt.oop.Type.EventType;
import static ru.sbt.mipt.oop.Command.LightCommands.turnTheLightOnCommand;

public class TurnOnAllTheLightsCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnAllTheLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Action action = (HomeObject homeObject) -> {
            if (homeObject instanceof Light) {
                turnTheLightOnCommand((Light) homeObject);
                LightEvent event = new LightEvent(homeObject.getId(), LightEventType.ON);
                LightEventHandler handler = new LightEventHandler(smartHome);
                handler.handleEvent(event);
            }
        };
        smartHome.execute(action);
    }
}
