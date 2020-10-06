package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public interface Action {
    void execute(HomeComponent component);
    ActionType getType();
}
