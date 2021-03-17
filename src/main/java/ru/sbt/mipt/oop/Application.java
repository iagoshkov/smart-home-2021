package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args) {
        SmartHome smartHome = new JSONSmartHomeReader("smart-home-1.js").readHome();
        ArrayList<EventHandler> handlers = new ArrayList<>();
        handlers.add(new LightEventHandler(smartHome));
        handlers.add(new DoorEventHandler(smartHome));
        EventLoop eventLoop = new EventLoop(smartHome, handlers);
        eventLoop.process();
    }


}
