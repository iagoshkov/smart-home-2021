package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        JSON tempJSON = new JSON("smart-home-1.js");
        Gson gson = new Gson();
        SmartHome smartHome = gson.fromJson(tempJSON.getData(), SmartHome.class);

        // начинаем цикл обработки событий
        Event eventTemp = new Event();
        LightIterator lightIterator = new LightIterator(smartHome);
        DoorIterator doorIterator = new DoorIterator(smartHome);
        List<Processor> processors = Arrays.asList(new LightEventProcessor(smartHome, lightIterator), new DoorEventProcessor(smartHome, doorIterator));

        while (eventTemp.getEvent() != null) {
            System.out.println("Got event: " + eventTemp.getEvent());
            for(Processor processor : processors){
                processor.processing(eventTemp);
            }
            EventProcessing nextEvent = new EventProcessing();
            eventTemp.setEvent(nextEvent.next(eventTemp.getEvent()));
        }
    }
}
