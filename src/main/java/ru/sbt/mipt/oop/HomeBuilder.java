package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.home.Room;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.SmartHomeReaderWriter;

import java.io.IOException;

public class HomeBuilder {

    public static void main(String[] args) {
        Room bathroom = new Room("bathroom");
        Room hall = new Room("hall");
        Room kitchen = new Room("kitchen");
        Room bedroom = new Room("bedroom");

        SmartHome smartHome = new SmartHome();

        smartHome.addDevice(new Light("0", false, bathroom));
        smartHome.addDevice(new Light("1", true, hall));
        smartHome.addDevice(new Light("2", false, hall));
        smartHome.addDevice(new Light("3", true, kitchen));
        smartHome.addDevice(new Light("4", true, kitchen));

        smartHome.addDevice(new Door("5", true, bedroom));
        smartHome.addDevice(new Door("6", true, hall));
        smartHome.addDevice(new Door("7", true, hall));
        smartHome.addDevice(new Door("8", true, kitchen));
        smartHome.addDevice(new Door("9", true, bedroom));

        try {
            SmartHomeReaderWriter.saveToJson(smartHome, Constants.OUTPUT_SMART_HOME_JSON_FILE_NAME);
        } catch (IOException e) {
            System.out.println("Failed to save smart home");
            e.printStackTrace();
        }
    }

}
