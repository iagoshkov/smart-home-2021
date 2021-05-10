
package ru.sbt.mipt.oop.Objects.HomeObjects;
import ru.sbt.mipt.oop.Action.Action;

public class Door extends HomeObject implements Actionable{
  
    private final boolean isOpen;
  
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
  
    @Override
    public void execute(Action action) {
        action.accept(this);
    }
  
}
