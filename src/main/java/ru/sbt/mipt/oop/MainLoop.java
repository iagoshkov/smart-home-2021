package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.RandomEventGenerator;
import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public class MainLoop {
    private final RandomEventGenerator randomEventGenerator;
    private final EventProcessor eventProcessor;

    public MainLoop(EventProcessor eventProcessor, RandomEventGenerator randomEventGenerator) throws IllegalArgumentException {
        if (eventProcessor == null || randomEventGenerator == null) throw new IllegalArgumentException();
        this.eventProcessor = eventProcessor;
        this.randomEventGenerator = randomEventGenerator;
    }

    public void run(SmartHome smartHome) {
        // Активируем сигнализацию
        eventProcessor.executeEvent(new SensorEvent(SensorEventType.ALARM_ACTIVATE, null), smartHome);
        // Входим в цикл обработки событий
        while(true) {
            SensorEvent event = randomEventGenerator.getNextSensorEvent();
            if (event == null) return;
            System.out.println("Got event: " + event);
            eventProcessor.executeEvent(event, smartHome);
        }
    }
}
