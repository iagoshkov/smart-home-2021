package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.events.actions.Action;

public interface Actionable {
    void execute(Action action);
}
