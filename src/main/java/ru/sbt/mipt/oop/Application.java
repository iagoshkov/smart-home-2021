package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.elements.HomeElementType;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.elements.alarm.AlarmState;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.init.HomeLoader;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.io.IOException;

public class Application {
    private static SmartHome smartHome;
    private static HomeLoader homeLoader;
    public static String ACTIVATION_CODE = "code";
    public static String INVALID_CODE = "invalid";

    public Application(HomeLoader homeLoader) {
        this.homeLoader = homeLoader;
    }

    public static void main(String... args) throws IOException {
        HomeLoader homeLoader = new JsonHomeLoader();
        Application application = new Application(homeLoader);
        application.run();
    }

    private void run() {
        // считываем состояние дома из файла
        try {
            SmartHome smartHome = homeLoader.load(new FileInputStream("smart-home-1.js"));
            smartHome.addHomeComponent(HomeElementType.ALARM, new AlarmSystem(new StringId("ALARM"), ACTIVATION_CODE, AlarmState.DEACTIVATED));
            Engine engine = new SmartHomeEngine(smartHome);
            engine.start();

        } catch (IOException e) {
            System.out.println("Error while loading from JSON");
        }
    }


}
