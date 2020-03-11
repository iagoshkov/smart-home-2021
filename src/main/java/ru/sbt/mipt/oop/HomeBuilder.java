package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.storage.HomeConditionGsonStorage;
import ru.sbt.mipt.oop.storage.HomeConditionStorage;

import java.util.Arrays;
import java.util.Collections;

public class HomeBuilder {
    private SmartHome smartHome = buildHome();

    private void dumpHome(HomeConditionStorage homeConditionStorage) {
        homeConditionStorage.saveHome(smartHome);
    }

    public static void main(String... args) {
        new HomeBuilder().dumpHome(new HomeConditionGsonStorage("output.js"));
    }

    private static SmartHome buildHome() {
        Room kitchen = new Room("kitchen");
        kitchen.addLights(Arrays.asList(new Light("1", false), new Light("2", true)));
        kitchen.addDoors(Collections.singletonList(new Door(false, "1")));

        Room bathroom = new Room("bathroom");
        bathroom.addLights(Collections.singletonList(new Light("3", true)));
        bathroom.addDoors(Collections.singletonList(new Door(false, "2")));

        Room bedroom = new Room("bedroom");
        bedroom.addLights(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)));
        bedroom.addDoors(Collections.singletonList(new Door(true, "3")));

        Room hall = new Room("hall");
        hall.addLights(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)));
        hall.addDoors(Collections.singletonList(new Door(false, "4")));

        return new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
    }

    public SmartHome getSmartHome() {
        return smartHome;
    }
}
