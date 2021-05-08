
package ru.sbt.mipt.oop.Objects.HomeObjects;

public class Door extends HomeObject{
  
    private boolean isOpen;
  
    public boolean isOpen() {
        return isOpen;
    }
      
    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Door(boolean isOpen, String id) {
        super(id);
        this.isOpen = isOpen;
    }
  
}
