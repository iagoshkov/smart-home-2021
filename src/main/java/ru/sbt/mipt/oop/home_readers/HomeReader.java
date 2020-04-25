package ru.sbt.mipt.oop.home_readers;

import ru.sbt.mipt.oop.home_components.SmartHome;

import java.io.IOException;

public interface HomeReader {
    public SmartHome read(String addr) throws IOException;
}

