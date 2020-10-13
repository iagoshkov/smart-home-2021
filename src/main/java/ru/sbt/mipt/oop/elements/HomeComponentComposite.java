package ru.sbt.mipt.oop.elements;

import java.util.Collection;
import java.util.function.Predicate;

public interface HomeComponentComposite {
    void addHomeComponent(ElementType type, HomeComponent component);
    Collection<? extends HomeComponent> getComponents(Predicate<? super HomeComponent> condition);
    HomeComponent getComponent(Predicate<? super HomeComponent> condition);
    int getElementCount(ElementType type);
}
