package ru.sbt.mipt.oop.Objects.HomeObjects;
import ru.sbt.mipt.oop.Action.Action;

public class Light extends HomeObject implements Actionable{
    private final boolean isOn;
  
    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public Light(String id, boolean isOn) {
        super(id);
        this.isOn = isOn;
    }
    
     @Override
    public void execute(Action action) {
        action.accept(this);
        }
}
