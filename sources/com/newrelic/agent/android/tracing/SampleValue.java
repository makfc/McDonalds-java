package com.newrelic.agent.android.tracing;

public class SampleValue {
    private boolean isDouble;
    private Double value = Double.valueOf(0.0d);

    public SampleValue(double value) {
        setValue(value);
    }

    public SampleValue(long value) {
        setValue(value);
    }

    public Number getValue() {
        if (this.isDouble) {
            return asDouble();
        }
        return asLong();
    }

    public Double asDouble() {
        return this.value;
    }

    public Long asLong() {
        return Long.valueOf(this.value.longValue());
    }

    public void setValue(double value) {
        this.value = Double.valueOf(value);
        this.isDouble = true;
    }

    public void setValue(long value) {
        this.value = Double.valueOf((double) value);
        this.isDouble = false;
    }

    public boolean isDouble() {
        return this.isDouble;
    }

    public void setDouble(boolean aDouble) {
        this.isDouble = aDouble;
    }
}
