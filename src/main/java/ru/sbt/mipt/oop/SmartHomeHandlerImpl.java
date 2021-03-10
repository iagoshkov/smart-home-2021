package ru.sbt.mipt.oop;

public abstract class SmartHomeHandlerImpl implements SmartHomeHandler {
    public SmartHomeHandlerImpl(SmartHome smartHome, OutputStream output) {
        this.smartHome = smartHome;
        this.output = output;
    }

    @Override
    public abstract void handleEvent(SensorEvent event);

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void setOutputStream(OutputStream output) {
        this.output = output;
    }

    protected SmartHome smartHome;
    protected OutputStream output;
}
