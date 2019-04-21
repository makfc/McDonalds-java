package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import com.newrelic.com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;

public class BaseHarvestable implements Harvestable {
    protected static final Type GSON_STRING_MAP_TYPE = new C41481().getType();
    private final Harvestable.Type type;

    /* renamed from: com.newrelic.agent.android.harvest.type.BaseHarvestable$1 */
    static class C41481 extends TypeToken<Map> {
        C41481() {
        }
    }

    public BaseHarvestable(Harvestable.Type type) {
        this.type = type;
    }

    public JsonElement asJson() {
        switch (this.type) {
            case OBJECT:
                return asJsonObject();
            case ARRAY:
                return asJsonArray();
            case VALUE:
                return asJsonPrimitive();
            default:
                return null;
        }
    }

    public Harvestable.Type getType() {
        return this.type;
    }

    public String toJsonString() {
        return asJson().toString();
    }

    public JsonArray asJsonArray() {
        return null;
    }

    public JsonObject asJsonObject() {
        return null;
    }

    public JsonPrimitive asJsonPrimitive() {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public void notEmpty(String argument) {
        if (argument == null || argument.length() == 0) {
            throw new IllegalArgumentException("Missing Harvestable field.");
        }
    }

    /* Access modifiers changed, original: protected */
    public void notNull(Object argument) {
        if (argument == null) {
            throw new IllegalArgumentException("Null field in Harvestable object");
        }
    }

    /* Access modifiers changed, original: protected */
    public String optional(String argument) {
        if (argument == null) {
            return "";
        }
        return argument;
    }
}
