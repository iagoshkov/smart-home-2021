package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) {
        SmartHome smartHome = new JSONSmartHomeReader("smart-home-1.js").readHome();
        EventLoop eventLoop = new EventLoop(smartHome);
        eventLoop.process();
    }


}
