package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.HashMap;

public class HomeBuilder {
    public static void main(String[] args) throws IOException {
        String output = "output.js";

        Room kitchen = createTestKitchen();
        Room bathroom = createTestBathroom();
        Room bedroom = createTestBedroom();
        Hall hall = createTestHall();

        HashMap<String, Room> testRooms = new HashMap<>();

        testRooms.put(kitchen.getName(), kitchen);
        testRooms.put(bathroom.getName(), bathroom);
        testRooms.put(bedroom.getName(), bedroom);
        testRooms.put(hall.getName(), hall);

        SmartHome smartHome = new SmartHome(testRooms);

        SmartHomeJsonLoader.createJSON(smartHome, output);
    }

    private static Hall createTestHall() {
        HashMap<String, Light> testLights = new HashMap<>();
        HashMap<String, Door> testDoors = new HashMap<>();

        testLights.put("7", new Light("7", false));
        testLights.put("8", new Light("8", false));
        testLights.put("9", new Light("9", false));

        testDoors.put("4", new Door("4", false));

        return new Hall(testLights, testDoors, "hall");
    }

    private static Room createTestBedroom() {
        HashMap<String, Light> testLights = new HashMap<>();
        HashMap<String, Door> testDoors = new HashMap<>();

        testLights.put("4", new Light("4", false));
        testLights.put("5", new Light("5", false));
        testLights.put("6", new Light("6", false));

        testDoors.put("3", new Door("3", false));

        return new Room(testLights, testDoors, "bedroom");
    }

    private static Room createTestBathroom() {
        HashMap<String, Light> testLights = new HashMap<>();
        HashMap<String, Door> testDoors = new HashMap<>();

        testLights.put("3", new Light("3", false));

        testDoors.put("2", new Door("2", false));

        return new Room(testLights, testDoors, "bathroom");
    }

    private static Room createTestKitchen() {
        HashMap<String, Light> testLights = new HashMap<>();
        HashMap<String, Door> testDoors = new HashMap<>();

        testLights.put("1", new Light("1", false));
        testLights.put("2", new Light("2", false));

        testDoors.put("1", new Door("1", false));

        return new Room(testLights, testDoors, "kitchen");
    }

}
