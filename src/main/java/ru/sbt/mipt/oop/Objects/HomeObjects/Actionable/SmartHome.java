package ru.sbt.mipt.oop.Objects.HomeObjects.Actionable;
import java.util.Collection;
import java.util.ArrayList;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Objects.Alarm.AlarmState;
import ru.sbt.mipt.oop.Objects.HomeObjects.HomeObject;

public class SmartHome extends HomeObject implements Actionable {
    Collection<Room> rooms;
        
    public Collection<Room> getRooms() {
        return rooms;
    }
        
    public void addRoom(Room room) {
        rooms.add(room);
    }
    
    AlarmState alarm;

    public SmartHome() {
        super("home");
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        super("home");
        this.rooms = rooms;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
        for (Room room: rooms) {
            room.execute(action);
        }
    }
}
