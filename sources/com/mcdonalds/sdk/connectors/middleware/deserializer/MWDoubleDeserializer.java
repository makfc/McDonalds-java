package com.mcdonalds.sdk.connectors.middleware.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import java.lang.reflect.Type;

public class MWDoubleDeserializer implements JsonDeserializer<Double>, CustomTypeAdapter<Double> {
    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.isJsonObject()) {
                return null;
            }
            return Double.valueOf(json.getAsDouble());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Class<Double> getType() {
        return Double.class;
    }

    public Object getDeserializer() {
        return this;
    }

    public Object getSerializer() {
        return null;
    }
}
