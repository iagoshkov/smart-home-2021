package ru.sbt.mipt.oop;

public class HomeBuilder {
    public static void main(String[] args) {
        SmartHomeCreator smartHomeCreator = new SomeSmartHomeCreator();
        SmartHome smartHome = smartHomeCreator.create();

        SmartHomeWriter smartHomeWriter = new JsonSmartHomeWriter("output.json");
        smartHomeWriter.write(smartHome);
    }
}
