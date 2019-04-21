package com.mcdonalds.sdk.connectors.middleware.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.utils.DateUtils;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

public class ISO8601DateDeserializer implements JsonDeserializer<Date>, JsonSerializer<Date>, CustomTypeAdapter<Date> {
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            return DateUtils.parseFromISO8631(jsonElement.getAsString(), false);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(DateUtils.formatToISO8631(src, false));
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
