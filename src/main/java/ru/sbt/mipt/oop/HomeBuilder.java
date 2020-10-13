package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smart.devices.Alarm;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.home.Room;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeWriter;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeWriterJsonFile;

import java.util.ArrayList;

public class HomeBuilder {
    public static void main(String[] args) {
        Room bathroom = new Room("bathroom", null);
        Room hall = new Room("hall", null);
        Room kitchen = new Room("kitchen", null);
        Room bedroom = new Room("bedroom", null);

        bathroom.addDevice(new Light("1", false));
        bathroom.addDevice(new Light("2", false));
        bathroom.addDevice(new Door("11", false));
        bathroom.addDevice(new Door("12", false));

        hall.addDevice(new Light("3", false));
        hall.addDevice(new Light("4", false));
        hall.addDevice(new Door("13", false));
        hall.addDevice(new Door("14", false));

        kitchen.addDevice(new Light("5", false));
        kitchen.addDevice(new Light("6", false));
        kitchen.addDevice(new Door("15", false));
        kitchen.addDevice(new Door("16", false));

        bedroom.addDevice(new Light("7", false));
        bedroom.addDevice(new Light("8", false));
        bedroom.addDevice(new Door("17", false));
        bedroom.addDevice(new Door("18", false));

        ArrayList<Room> roomsList = new ArrayList<>();
        roomsList.add(bathroom);
        roomsList.add(hall);
        roomsList.add(kitchen);
        roomsList.add(bedroom);

        SmartHome smartHome = new SmartHome(new Alarm("99", Constants.ALARM_PASSWORD), roomsList);

        SmartHomeWriter smartHomeReaderWriter = new SmartHomeWriterJsonFile(
                Constants.OUTPUT_SMART_HOME_JSON_FILE_NAME);
        boolean saveSuccessful = smartHomeReaderWriter.save(smartHome);
        if (!saveSuccessful)
            System.out.println("Error save smart home");
    }
}