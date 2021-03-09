package ru.sbt.mipt.oop;


// interface HomeProvider is used to separate getting SmartHome logic
public interface HomeProvider {
    SmartHome provideHome();
}
