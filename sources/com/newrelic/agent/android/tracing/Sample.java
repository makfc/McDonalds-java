package com.newrelic.agent.android.tracing;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import com.newrelic.com.google.gson.JsonArray;

public class Sample extends HarvestableArray {
    private SampleValue sampleValue;
    private long timestamp;
    private SampleType type;

    public enum SampleType {
        MEMORY,
        CPU
    }

    public Sample(SampleType type) {
        setSampleType(type);
        setTimestamp(System.currentTimeMillis());
    }

    public Sample(long timestamp) {
        setTimestamp(timestamp);
    }

    public Sample(long timestamp, SampleValue sampleValue) {
        setTimestamp(timestamp);
        setSampleValue(sampleValue);
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public SampleValue getSampleValue() {
        return this.sampleValue;
    }

    public void setSampleValue(SampleValue sampleValue) {
        this.sampleValue = sampleValue;
    }

    public void setSampleValue(double value) {
        this.sampleValue = new SampleValue(value);
    }

    public void setSampleValue(long value) {
        this.sampleValue = new SampleValue(value);
    }

    public Number getValue() {
        return this.sampleValue.getValue();
    }

    public SampleType getSampleType() {
        return this.type;
    }

    public void setSampleType(SampleType type) {
        this.type = type;
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(SafeJsonPrimitive.factory(Long.valueOf(this.timestamp)));
        jsonArray.add(SafeJsonPrimitive.factory(this.sampleValue.getValue()));
        return jsonArray;
    }
}
