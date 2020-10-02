package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.smart.actions.Action;

public interface Actionable {
    void execute(Action action);
}
