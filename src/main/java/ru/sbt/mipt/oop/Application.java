package ru.sbt.mipt.oop;

// class Application is used to set up specific realizations of interfaces
// and to start smart home activity
public class Application {

    public static void main(String... args) {

        HomeControl homeControl = new HomeControlSimulator();
        HomeSupervision homeSupervision = new HomeSupervisionSimulator();

        HomeProvider homeProvider = new JsonHomeProvider("smart-home-1.js");
        SmartHome smartHome = homeProvider.provideHome();

        EventHandler eventHandler = new SmartHomeEventHandler(homeControl, smartHome);

        HomeRunner homeRunner = new SmartHomeRunner(homeSupervision, eventHandler);
        homeRunner.runHome();
    }
}
