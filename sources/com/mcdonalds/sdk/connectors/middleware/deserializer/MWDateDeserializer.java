package com.mcdonalds.sdk.connectors.middleware.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import java.lang.reflect.Type;
import java.util.Date;

public class MWDateDeserializer implements JsonDeserializer<Date>, CustomTypeAdapter<Date> {
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Date(jsonElement.getAsLong() * 1000);
    }

    public Class<Date> getType() {
        return Date.class;
    }

    public Object getDeserializer() {
        return this;
    }

    public Object getSerializer() {
        return this;
    }
}
