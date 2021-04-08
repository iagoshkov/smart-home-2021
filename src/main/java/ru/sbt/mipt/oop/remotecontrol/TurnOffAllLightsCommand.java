package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;
public class TurnOffAllLightsCommand implements RemoteControlCommand {

    private final SmartHome smartHome;

    public TurnOffAllLightsCommand(SmartHome smartHome) {

        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(component ->{
            if(component instanceof Light){
                ((Light) component).setOn(false);
            }
        });
    }

}

