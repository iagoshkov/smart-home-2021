package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.HomeComponent;

@FunctionalInterface
public interface Action {
    void act(HomeComponent homeComponent);
}
