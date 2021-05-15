  
package ru.sbt.mipt.oop.Handler.Decorator;
import java.util.List; 
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Handler.SensorEventHandler;
import ru.sbt.mipt.oop.Objects.Alarm.SmartAlarm;

public abstract class AlarmSensorEventHandlerInterface {
   void handleEvent(SensorEvent event);
}
