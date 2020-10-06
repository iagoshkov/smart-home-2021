package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.events.Event;

public interface Actionable {
    Event apply(Event event, Action action);
}
