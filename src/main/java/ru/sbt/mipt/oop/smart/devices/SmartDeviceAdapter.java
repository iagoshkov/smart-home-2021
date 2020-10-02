package ru.sbt.mipt.oop.smart.devices;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class SmartDeviceAdapter implements JsonSerializer<SmartDevice>, JsonDeserializer<SmartDevice> {
    public JsonElement serialize(SmartDevice src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("deviceClass", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));
        return result;
    }

    public SmartDevice deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String deviceClass = jsonObject.get("deviceClass").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element, Class.forName("ru.sbt.mipt.oop.smart.devices." + deviceClass));
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown element type: " + deviceClass, e);
        }
    }
}