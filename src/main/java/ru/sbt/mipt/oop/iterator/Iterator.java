package ru.sbt.mipt.oop.iterator;

import java.util.function.Consumer;

public interface Iterator<T> {
    T getNext();
    boolean hasNext();
    void forEach(Consumer<T> func);
}
