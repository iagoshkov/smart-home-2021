package ru.sbt.mipt.oop;

public class Light {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }
    //This function get us information - first responsibility
    /*
    public boolean isOn() {
        return isOn;
    }
    */
    //this function get us information too
    /*
    public String getId() {
        return id;
    }
    */

    //this function set up data for out clas Light - second responsibility
    public void setOn(boolean on) {
        isOn = on;
    }
}

// We need special class that has main responsibility - to get information (SRP)
public class LightInformation extends Light{
    public boolean isOn() {
        return isOn;
    }
    
    public String getId() {
        return id;
    }
    
}
