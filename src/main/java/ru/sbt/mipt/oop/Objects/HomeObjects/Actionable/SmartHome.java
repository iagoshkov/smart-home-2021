package ru.sbt.mipt.oop.Objects.HomeObjects.Actionable;
import java.util.ArrayList;
import java.util.Collection;
import ru.sbt.mipt.oop.Action.Action;
import ru.sbt.mipt.oop.Objects.Alarm.AlarmState;
import ru.sbt.mipt.oop.Objects.HomeObjects.HomeObject;
import java.util.Arrays;
import java.util.List;

public class SmartHome extends HomeObject implements Actionable {
    List<Room> rooms;
        
    public List<Room> getRooms() {
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

    public SmartHome(List<Room> rooms) {
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
