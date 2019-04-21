package com.mcdonalds.sdk.services.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mcdonalds.sdk.connectors.utils.SerializableSparseArray;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map.Entry;

public class SerializableSparseArraySerializer<T> implements JsonDeserializer<SerializableSparseArray<T>>, JsonSerializer<SerializableSparseArray<T>> {
    public SerializableSparseArray<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject() && (typeOfT instanceof ParameterizedType)) {
            ParameterizedType type = (ParameterizedType) typeOfT;
            SerializableSparseArray<T> sparseArray = new SerializableSparseArray();
            for (Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                Integer key = Integer.valueOf(Integer.parseInt((String) entry.getKey()));
                sparseArray.put(key.intValue(), context.deserialize((JsonElement) entry.getValue(), type.getActualTypeArguments()[0]));
            }
            return sparseArray;
        }
        throw new JsonParseException("Expected Object type for SerializeSparseArray");
    }

    public JsonElement serialize(SerializableSparseArray<T> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        for (int i = 0; i < src.size(); i++) {
            Integer key = Integer.valueOf(src.keyAt(i));
            jsonObject.add(key.toString(), context.serialize(src.valueAt(i)));
        }
        return jsonObject;
    }
}
