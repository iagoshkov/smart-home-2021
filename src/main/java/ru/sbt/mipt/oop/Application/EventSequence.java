  
package ru.sbt.mipt.oop.Application;
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Generator.NextSensorEventGenerator;
import ru.sbt.mipt.oop.Handler.SensorEventHandler;
import java.util.List;

public class EventSequence {
    private NextSensorEventGenerator nextSensorEventGenerator;
    private ArrayList<SensorEventHandler> sensorEventHandlers;

    public EventSequence(NextSensorEventGenerator nextSensorEventGenerator, List<SensorEventHandler> sensorEventHandlers) {
        this.nextSensorEventGenerator = nextSensorEventGenerator;
        this.sensorEventHandlers = sensorEventHandlers;
    }

    public void run() {
        SensorEvent event;
        while (event != null) {
            event = nextSensorEventGenerator.getNextSensorEvent();
            System.out.println("Got event: " + event);
            for (SensorEventHandler handler: sensorEventHandlers) {
                handler.handleEvent(event);
            }
        }
    }
}
