package ru.sbt.mipt.oop.Adapter;
import ru.sbt.mipt.oop.Handler.*;
import ru.sbt.mipt.oop.Handler.Decorator.AlarmSensorEventHandler;
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Objects.alarm.SmartAlarm;
import ru.sbt.mipt.oop.objects.HomeObject.Actionable.SmartHome;
import com.coolcompany.SmartHome.Event.CCSensorEvent;
import com.coolcompany.SmartHome.Event.EventHandler;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CCSensorEventAdapter implements EventHandler {
    private SmartHome smartHome;
    private SmartAlarm alarm;
    private HashMap<String, CCEventCreator> map;
    private ArrayList<SensorEventHandler> handler;

    public CCSensorEventAdapter(SmartHome smartHome, SmartAlarm alarm, Map<String, CCEventCreator> map, List<SensorEventHandler> handler) {
        this.smartHome = smartHome;
        this.alarm = alarm;
        this.map = map;
        this.handler = handler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        String type = event.getEventType();
        if (map.containsKey(type)) {
           String type = event.getEventType();
        } else {
          String type = event.getEventType();
          SensorEvent sensorEvent = map.get(type).create(event.getObjectId());
        for (SensorEventHandler handler : handler) {
            handler.handleEvent(sensorEvent);
        }
        }
    }
}
