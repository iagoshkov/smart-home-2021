package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.persister.HomeConditionGsonPersister;
import ru.sbt.mipt.oop.persister.HomeConditionPersister;
import ru.sbt.mipt.oop.sensorEvent.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Application {
    private final HomeConditionPersister homeConditionPersister;

    private Application(HomeConditionPersister homeConditionPersister) {
        this.homeConditionPersister = homeConditionPersister;
    }

    public static void main(String... args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        new Application(new HomeConditionGsonPersister("smart-home-1.js")).handleEvents();
    }

    private static SensorEvent getNextSensorEvent() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?>[] classes = new Class[]{SensorDoorOpenEvent.class, SensorDoorClosedEvent.class, SensorLightOnEvent.class, SensorLightOffEvent.class, SensorHallDoorClosedEvent.class};
        if (Math.random() < 0.05) return null;
        Class<?> choice = classes[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        Constructor<?> constructor = choice.getConstructor(String.class);
        Object event = constructor.newInstance(objectId);
        return (SensorEvent) event;
    }

    private void handleEvents() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        EventHandler handler = new EventHandler(homeConditionPersister.readHome());
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            handler.handleEvent(event);
            event = getNextSensorEvent();
        }
    }
}
