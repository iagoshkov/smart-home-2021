package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.home.locations.Room;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeJsonReaderWriter;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeReaderWriter;

public class HomeBuilder {
    public static void main(String[] args) {
        Room bathroom = new Room("bathroom");
        Room hall = new Room("hall");
        Room kitchen = new Room("kitchen");
        Room bedroom = new Room("bedroom");
        SmartHome smartHome = new SmartHome("0", "Smart home");
        smartHome.addDevice(new Light("1", false, bathroom));
        smartHome.addDevice(new Light("2", true, hall));
        smartHome.addDevice(new Light("3", false, hall));
        smartHome.addDevice(new Light("4", true, kitchen));
        smartHome.addDevice(new Light("5", true, kitchen));
        smartHome.addDevice(new Door("6", true, bedroom));
        smartHome.addDevice(new Door("7", true, hall));
        smartHome.addDevice(new Door("8", true, hall));
        smartHome.addDevice(new Door("9", true, kitchen));
        smartHome.addDevice(new Door("10", true, bedroom));

        try {
            SmartHomeReaderWriter smartHomeReaderWriter = new SmartHomeJsonReaderWriter(
                    Constants.INPUT_SMART_HOME_JSON_FILE_NAME,
                    Constants.OUTPUT_SMART_HOME_JSON_FILE_NAME);
            smartHomeReaderWriter.saveSmartHome(smartHome);
        } catch (RuntimeException e) {
            System.out.println("Failed to save smart home");
            e.printStackTrace();
        }
    }
}