package ru.sbt.mipt.oop;

// class Application is used to set up specific realizations of interfaces
// and to start smart home activity
public class Application {

    public static void main(String... args) {

        HomeInteraction homeInteraction = new HomeInteractionSimulator();

        HomeProvider homeProvider = new JsonHomeProvider("smart-home-1.js");
        SmartHome smartHome = homeProvider.provideHome();

        EventHandler eventHandler = new SmartHomeEventHandler(homeInteraction, smartHome);

        HomeRunner homeRunner = new SmartHomeRunner(homeInteraction, eventHandler);
        homeRunner.runHome();
    }
}
