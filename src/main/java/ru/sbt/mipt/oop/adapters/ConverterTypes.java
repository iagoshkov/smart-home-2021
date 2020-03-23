package ru.sbt.mipt.oop.adapters;

import ru.sbt.mipt.oop.event.SensorEventType;

import java.util.Map;

public class  ConverterTypes{
    public static final Map<String, SensorEventType> convertType = Map.of(
            "LightIsOn", SensorEventType.LIGHT_ON,
            "LightIsOff", SensorEventType.LIGHT_OFF,
            "DoorIsOpen", SensorEventType.DOOR_OPEN,
            "DoorIsClosed", SensorEventType.DOOR_CLOSED
    );
}
