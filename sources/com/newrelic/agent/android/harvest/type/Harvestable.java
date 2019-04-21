package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;

public interface Harvestable {

    public enum Type {
        OBJECT,
        ARRAY,
        VALUE
    }

    JsonElement asJson();

    JsonArray asJsonArray();

    JsonObject asJsonObject();

    JsonPrimitive asJsonPrimitive();

    Type getType();

    String toJsonString();
}
