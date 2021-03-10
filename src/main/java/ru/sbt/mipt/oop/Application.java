package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        SmartHome smartHome = (new SmartHomeFromJSGetter("smart-home-1.js")).get();
        // начинаем цикл обработки событий
        ProcessEvents(smartHome);
    }

    private static void ProcessEvents(SmartHome smartHome) {
        SensorEvent event = getNextSensorEvent();
        DoorCloser doorCloser = new DoorCloser(smartHome);
        LightSwitcher lightSwitcher = new LightSwitcher(smartHome);

        while (event != null) {
            System.out.println("Got event: " + event);

            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                // событие от источника света
                if (event.getType() == LIGHT_ON) {
                    lightSwitcher.turnOn(event.getObjectId());

                } else {
                    lightSwitcher.turnOff(event.getObjectId());
                }
            }

            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                // событие от двери
                if (event.getType() == DOOR_OPEN) {
                    doorCloser.open(event.getObjectId());
                } else {
                    Room roomWhereClosed = doorCloser.close(event.getObjectId());
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    if (roomWhereClosed != null && roomWhereClosed.getName().equals("hall")) {
                        lightSwitcher.turnOffAll();
                        CommandSender sender = new CommandSender(smartHome);
                        sender.sendAllLight();
                    }
                }
            }
            event = getNextSensorEvent();
        }
    }


    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
