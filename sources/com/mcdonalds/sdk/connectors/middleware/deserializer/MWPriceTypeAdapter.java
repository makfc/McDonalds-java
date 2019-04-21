package com.mcdonalds.sdk.connectors.middleware.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mcdonalds.sdk.connectors.middleware.model.MWPriceType;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import java.lang.reflect.Type;

public class MWPriceTypeAdapter implements JsonDeserializer<MWPriceType>, JsonSerializer<MWPriceType>, CustomTypeAdapter<MWPriceType> {
    public Class<MWPriceType> getType() {
        return MWPriceType.class;
    }

    public Object getDeserializer() {
        return this;
    }

    public Object getSerializer() {
        return this;
    }

    public MWPriceType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return MWPriceType.values()[jsonElement.getAsInt()];
    }

    public JsonElement serialize(MWPriceType ecpPriceType, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(ecpPriceType.integerValue());
    }
}
