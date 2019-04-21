package com.newrelic.agent.android.harvest.type;

import com.newrelic.com.google.gson.JsonPrimitive;

public class HarvestableDouble extends HarvestableValue {
    private double value;

    public HarvestableDouble(double value) {
        this();
        this.value = value;
    }

    public JsonPrimitive asJsonPrimitive() {
        return new JsonPrimitive(Double.valueOf(this.value));
    }
}
