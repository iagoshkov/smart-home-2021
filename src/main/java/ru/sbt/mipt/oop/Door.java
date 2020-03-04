package ru.sbt.mipt.oop;

public class Door {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }
    /* It is first responsibility: 
    to get information about Door => 
    It is better to create new class
    "DoorInformation" with this 
    responsibility
    public String getId() {
        return id;
    }
    */
    //It is second responsibility: to set up information about Door
    public void setOpen(boolean open) {
        isOpen = open;
    }
    
}
//We need special class that has main responsibility - to get information 
public class DoorInformation extends Door{
    public String getId() {
        return id;
    }
}


