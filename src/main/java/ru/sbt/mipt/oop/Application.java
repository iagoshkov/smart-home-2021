package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.EventGenerator;
import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeJsonReaderWriter;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeReaderWriter;

import java.io.IOException;

public class Application {
    private final SmartHomeReaderWriter smartHomeReaderWriter;

    public Application(SmartHomeReaderWriter smartHomeReaderWriter) {
        this.smartHomeReaderWriter = smartHomeReaderWriter;
    }

    public static void main(String... args) {
        SmartHomeReaderWriter smartHomeReaderWriter = new SmartHomeJsonReaderWriter(
                Constants.INPUT_SMART_HOME_JSON_FILE_NAME, Constants.OUTPUT_SMART_HOME_JSON_FILE_NAME);
        Application application = new Application(smartHomeReaderWriter);

        application.start();
    }

    public void start() {
        SmartHome smartHome;
        try {
            smartHome = smartHomeReaderWriter.loadSmartHome();
        } catch (IOException e) {
            System.out.println("Failed to initialize smart home");
            e.printStackTrace();
            return;
        }
        MainLoop mainLoop = new MainLoop(new EventProcessor(), new EventGenerator());
        mainLoop.run(smartHome);
    }
}