package ru.sbt.mipt.oop.persister;

import ru.sbt.mipt.oop.objects.SmartHome;

import java.io.IOException;

public interface HomeConditionPersister {
    SmartHome readHome(String filename) throws IOException;
    void saveHome(SmartHome smartHome, String filename) throws IOException;
}
