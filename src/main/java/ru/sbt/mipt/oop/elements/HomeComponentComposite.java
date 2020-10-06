package ru.sbt.mipt.oop.elements;

import java.util.Collection;

public interface HomeComponentComposite {
    void addHomeComponent(ElementType type, HomeComponent component);
    Collection<? extends HomeComponent> getComponents(ElementType type);
    HomeComponent getComponent(ElementType type, ComponentId id);
    int getElementCount(ElementType type);
}
