package ru.sbt.mipt.oop;

interface ReceiverSmartHomeEvents {
    void handleEvents(EventsSource eventsSource, SmartHome executor, OutputStream output);
}
