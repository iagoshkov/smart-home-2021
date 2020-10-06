package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;

public interface Actionable {
    Action apply(Action action, ComponentId component);
}
