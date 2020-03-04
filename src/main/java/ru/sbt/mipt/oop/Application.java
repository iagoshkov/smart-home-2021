package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import java.io.IOException;

public class Application {
    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        JSON tempJSON = new JSON("smart-home-1.js");

        Gson gson = new Gson();
        SmartHome smartHome = gson.fromJson(tempJSON.getJSON(), SmartHome.class);

        // начинаем цикл обработки событий
        Event eventTemp = new Event();
        while (eventTemp.getEvent() != null) {
            eventTemp.print(smartHome);
            eventTemp.next();
        }
    }
}
