package ru.sbt.mipt.oop.condition;

import ru.sbt.mipt.oop.home.SmartHome;

import java.io.IOException;

public interface HomeCondition {
    public SmartHome smartHomeCondition() throws IOException;
}
