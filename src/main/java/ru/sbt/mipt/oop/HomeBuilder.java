package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.io.JsonSmartHomeWriter;
import ru.sbt.mipt.oop.io.SmartHomeWriter;

public class HomeBuilder {
    public static void main(String[] args) {
        SmartHomeCreator smartHomeCreator = new SmartHomeCreator();
        SmartHome smartHome = smartHomeCreator.create();

        SmartHomeWriter smartHomeWriter = new JsonSmartHomeWriter("output.json");
        smartHomeWriter.write(smartHome);
    }
}
