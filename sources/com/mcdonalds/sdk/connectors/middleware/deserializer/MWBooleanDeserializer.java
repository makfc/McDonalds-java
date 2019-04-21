package com.mcdonalds.sdk.connectors.middleware.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import java.lang.reflect.Type;

public class MWBooleanDeserializer implements JsonDeserializer<Boolean>, CustomTypeAdapter<Boolean> {
    public Boolean deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String booleanTestString = jsonElement.getAsString().toUpperCase();
        if (booleanTestString.equals(DCSProfile.INDICATOR_TRUE) || booleanTestString.equals("T")) {
            return Boolean.TRUE;
        }
        if (booleanTestString.equals(DCSProfile.INDICATOR_FALSE) || booleanTestString.equals("F")) {
            return Boolean.FALSE;
        }
        return null;
    }

    public Class<Boolean> getType() {
        return Boolean.class;
    }

    public Object getDeserializer() {
        return this;
    }

    public Object getSerializer() {
        return null;
    }
}
