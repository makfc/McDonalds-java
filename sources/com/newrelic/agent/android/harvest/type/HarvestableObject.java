package com.newrelic.agent.android.harvest.type;

import com.newrelic.agent.android.harvest.type.Harvestable.Type;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonObject;
import java.util.Map;

public abstract class HarvestableObject extends BaseHarvestable {
    public abstract JsonObject asJsonObject();

    public static HarvestableObject fromMap(final Map<String, String> map) {
        return new HarvestableObject() {
            public JsonObject asJsonObject() {
                return (JsonObject) new Gson().toJsonTree(map, GSON_STRING_MAP_TYPE);
            }
        };
    }

    public HarvestableObject() {
        super(Type.OBJECT);
    }
}
