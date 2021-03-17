package ru.sbt.mipt.oop;

public interface Action {
    <T extends HomeComponent> void act(T component);
}
