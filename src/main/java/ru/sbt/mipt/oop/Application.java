package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.RandomEventGenerator;
import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeReader;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeReaderJsonFile;

public class Application {
    private final SmartHomeReader smartHomeReader;

    public Application(SmartHomeReader smartHomeReader) {
        this.smartHomeReader = smartHomeReader;
    }

    public static void main(String... args) {
        SmartHomeReader smartHomeReader = new SmartHomeReaderJsonFile(Constants.INPUT_SMART_HOME_JSON_FILE_NAME);
        Application application = new Application(smartHomeReader);

        application.start();
    }

    public void start() {
        SmartHome smartHome = smartHomeReader.load();
        if (smartHome == null) {
            System.out.println("Error load smart home");
        }

        MainLoop mainLoop = new MainLoop(new EventProcessor(), new RandomEventGenerator());
        mainLoop.run(smartHome);
    }
}