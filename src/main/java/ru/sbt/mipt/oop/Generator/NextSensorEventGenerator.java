package ru.sbt.mipt.oop.Generator;
import ru.sbt.mipt.oop.Event.SensorEvent;
import ru.sbt.mipt.oop.Generator.EventGenerator.AlarmEventGenerator;
import ru.sbt.mipt.oop.Generator.EventGenerator.DoorEventGenerator;
import ru.sbt.mipt.oop.Generator.EventGenerator.EventGenerator;
import ru.sbt.mipt.oop.Generator.EventGenerator.LightEventGenerator;
import java.util.Arrays;
import java.util.List;


public class NextSensorEventGenerator {
   
    public SensorEvent getNextSensorEvent() {
        if (Math.random() < 0.025) return null; 
        if (Math.random() < 0.05) return new AlarmEventGenerator().Generate(((Integer)(int)(2 * Math.random())).toString());
        List<EventGenerator> eventGenerators = Arrays.asList(new DoorEventGenerator(), new LightEventGenerator());
        ObjectIdGenerator objectIdGenerator = new ObjectIdGenerator();
        return eventGenerators.get((int) (2 * Math.random())).Generate(objectIdGenerator.Generate());
    }
}
