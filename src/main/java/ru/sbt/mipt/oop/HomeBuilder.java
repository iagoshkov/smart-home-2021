package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.SmartHomeWriter.JsonSmartHomeWriter;
import ru.sbt.mipt.oop.SmartHomeWriter.SmartHomeWriter;

import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) {
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3")),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door(false, "4")),
                "hall");

        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall), "hall");

        String filename = "output.json";
        SmartHomeWriter smartHouseWriter = new JsonSmartHomeWriter(filename);
        smartHouseWriter.write(smartHome);
    }

}
