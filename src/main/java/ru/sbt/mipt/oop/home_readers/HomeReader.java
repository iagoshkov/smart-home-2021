package ru.sbt.mipt.oop.home_readers;

import ru.sbt.mipt.oop.home_components.SmartHome;

public interface HomeReader {
    public SmartHome read(String addr);
}

