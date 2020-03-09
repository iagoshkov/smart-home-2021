package ru.sbt.mipt.oop.iterator;

import java.util.function.Consumer;

interface Iterable<T> {
    T getNext();
    boolean hasNext();
    void forEach(Consumer<T> func);
}
