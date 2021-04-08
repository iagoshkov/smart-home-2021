package ru.sbt.mipt.oop;


public class Application {

    public static void main(String... args) {

        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");

        SensorEventCreator sensorEventCreator = new SensorEventCreator(smartHome);
        sensorEventCreator.SensorEventCreatorLoop();
    }
}

