package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.Room;
import ru.sbt.mipt.oop.devices.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHomeReader reader = new SmartHomeFromJsonFileReader("smart-home-1.js");
        SmartHome smartHome = reader.read();
        SmartHomeDispatcher dispatcher = new SmartHomeDispatcher(smartHome);
        dispatcher.start();
    }
}
