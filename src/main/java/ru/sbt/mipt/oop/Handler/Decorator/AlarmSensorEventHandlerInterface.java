  
package ru.sbt.mipt.oop.Handler.Decorator;
import java.util.List; 
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Handler.SensorEventHandler;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;

public interface AlarmSensorEventHandlerInterface {
   public AlarmSensorEventHandler(SmartAlarm alarm, List<SensorEventHandler> handlers);
   void handleEvent(SensorEvent event);
}
