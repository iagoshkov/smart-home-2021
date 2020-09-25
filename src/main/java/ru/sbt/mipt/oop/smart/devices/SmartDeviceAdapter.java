package ru.sbt.mipt.oop.smart.devices;

import com.google.gson.*;

import java.lang.reflect.Type;

public class SmartDeviceAdapter implements JsonSerializer<SmartDevice>, JsonDeserializer<SmartDevice> {
    @Override
    public JsonElement serialize(SmartDevice src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("deviceClass", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public SmartDevice deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String deviceClass = jsonObject.get("deviceClass").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element, Class.forName("ru.sbt.mipt.oop.smart.devices." + deviceClass));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + deviceClass, cnfe);
        }
    }
}
