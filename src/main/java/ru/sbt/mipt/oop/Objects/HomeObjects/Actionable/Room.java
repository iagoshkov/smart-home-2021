package ru.sbt.mipt.oop.Objects.HomeObjects.Actionable;
import java.util.Collection;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Objects.HomeObjects.*;
import java.util.Arrays;
import java.util.List;

public class Room extends HomeObject implements Actionable {
    private final String name;
    
    public String getName() {
        return name;
    }
    
    private List<Door> doors;
    
    public List<Door> getDoors() {
        return doors;
    }
    
    private List<Light> lights;
    
    public List<Light> getLights() {
        return lights;
        
    }

    public Room(String name, List<Door> doors, List<Light> lights) {
        this.name = name;
        this.doors = doors;
        this.lights = lights;
        super(name);   
        
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
        for (Door door : doors) {
            action.accept(door);
        }
        for (Light light : lights) {
            action.accept(light);
        }
    }
}
