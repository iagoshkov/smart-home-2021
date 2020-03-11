package ru.sbt.mipt.oop.storage;

import ru.sbt.mipt.oop.objects.SmartHome;

public interface HomeConditionStorage {
    SmartHome readHome();
    void saveHome(SmartHome smartHome);
}
