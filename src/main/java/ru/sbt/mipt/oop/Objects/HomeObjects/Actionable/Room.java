package ru.sbt.mipt.oop.Objects.HomeObjects.Actionable;
import java.util.Collection;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Objects.HomeObjects.*;

public class Room extends HomeObject implements Actionable {
    private String name;
    
    public String getName() {
        return name;
    }
    
    private Collection<Door> doors;
    
    public Collection<Door> getDoors() {
        return doors;
    }
    
    private Collection<Light> lights;
    
    public Collection<Light> getLights() {
        return lights;
        
    }

    public Room(String name, Collection<Door> doors, Collection<Light> lights) {
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
