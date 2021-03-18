package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class HomeBuilder {
    public static void main(String[] args) throws IOException {
        String output = "output.js";

        Room kitchen = createTestKitchen();
        Room bathroom = createTestBathroom();
        Room bedroom = createTestBedroom();
        Hall hall = createTestHall();

        Collection<Room> testRooms;

        testRooms.add(kitchen);
        testRooms.add(bathroom);
        testRooms.add(bedroom);
        testRooms.add(hall);

        SmartHome smartHome = new SmartHome(testRooms);

        SmartHomeJsonLoader.createJSON(smartHome, output);
    }

    private static Hall createTestHall() {
        Collection<Light> testLights = Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false));
        Collection<Door> testDoors = Arrays.asList(new Door("4", false));

        return new Hall(testLights, testDoors, "hall");
    }

    private static Room createTestBedroom() {
        Collection<Light> testLights = Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false));
        Collection<Door> testDoors = Arrays.asList(new Door("3", false));

        return new Room(testLights, testDoors, "bedroom");
    }

    private static Room createTestBathroom() {
        Collection<Light> testLights = Arrays.asList(new Light("3", false));
        Collection<Door> testDoors = Arrays.asList(new Door("2", false));

        return new Room(testLights, testDoors, "bathroom");
    }

    private static Room createTestKitchen() {
        Collection<Light> testLights = Arrays.asList(new Light("1", false), new Light("2", false));
        Collection<Door> testDoors = Arrays.asList(new Door("1", false));

        return new Room(testLights, testDoors, "kitchen");
    }
}
