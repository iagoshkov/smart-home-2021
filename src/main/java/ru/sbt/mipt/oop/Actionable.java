package ru.sbt.mipt.oop;

import java.util.function.Consumer;

public interface Actionable {
    void execute(Consumer<Object> action);
}
