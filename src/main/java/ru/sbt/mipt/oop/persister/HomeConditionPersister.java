package ru.sbt.mipt.oop.persister;

import ru.sbt.mipt.oop.objects.SmartHome;

import java.io.IOException;

public interface HomeConditionPersister {
    SmartHome readHome();
    void saveHome(SmartHome smartHome);
}
