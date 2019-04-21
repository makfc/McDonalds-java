package com.mcdonalds.sdk.services.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mcdonalds.sdk.services.data.sync.CatalogVersionType;
import java.lang.reflect.Type;

public class CatalogVersionTypeTypeAdapter implements JsonDeserializer<CatalogVersionType>, JsonSerializer<CatalogVersionType>, CustomTypeAdapter<CatalogVersionType> {
    public Class<CatalogVersionType> getType() {
        return CatalogVersionType.class;
    }

    public Object getDeserializer() {
        return this;
    }

    public Object getSerializer() {
        return this;
    }

    public CatalogVersionType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return CatalogVersionType.fromValue(jsonElement.getAsInt());
    }

    public JsonElement serialize(CatalogVersionType ecpCatalogVersionType, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(Integer.valueOf(ecpCatalogVersionType.getIntegerValue()));
    }
}
