package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Map;

public class Event extends HarvestableArray {
    private long eventName;
    private Map<String, String> params = new HashMap();
    private long timestamp;

    public JsonArray asJsonArray() {
        JsonArray array = new JsonArray();
        array.add(new JsonPrimitive(Long.valueOf(this.timestamp)));
        array.add(new JsonPrimitive(Long.valueOf(this.eventName)));
        array.add(new Gson().toJsonTree(this.params, GSON_STRING_MAP_TYPE));
        return array;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getEventName() {
        return this.eventName;
    }

    public void setEventName(long eventName) {
        this.eventName = eventName;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
