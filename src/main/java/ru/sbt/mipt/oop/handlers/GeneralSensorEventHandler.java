package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GeneralSensorEventHandler implements SensorEventHandler{
    private final SmartHome smartHome;
    private final SensorEvent event;
    private Collection<SensorEventHandler> handlers;

    public GeneralSensorEventHandler(SmartHome smartHome, SensorEvent event, Collection<SensorEventHandler> handlers) {
        this.smartHome = smartHome;
        this.event = event;
        this.handlers = handlers;
    }

    @Override
    public void handleEvent() {
        handlers.forEach(SensorEventHandler::handleEvent);
    }
}