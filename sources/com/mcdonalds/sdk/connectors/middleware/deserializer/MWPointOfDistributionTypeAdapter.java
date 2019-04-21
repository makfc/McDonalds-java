package com.mcdonalds.sdk.connectors.middleware.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mcdonalds.sdk.connectors.middleware.model.MWPointOfDistribution;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import java.lang.reflect.Type;

public class MWPointOfDistributionTypeAdapter implements JsonDeserializer<MWPointOfDistribution>, JsonSerializer<MWPointOfDistribution>, CustomTypeAdapter<MWPointOfDistribution> {
    public Class<MWPointOfDistribution> getType() {
        return MWPointOfDistribution.class;
    }

    public Object getDeserializer() {
        return this;
    }

    public Object getSerializer() {
        return this;
    }

    public MWPointOfDistribution deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return MWPointOfDistribution.values()[jsonElement.getAsInt()];
    }

    public JsonElement serialize(MWPointOfDistribution ecpPointOfDistribution, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(ecpPointOfDistribution.integerValue());
    }
}
