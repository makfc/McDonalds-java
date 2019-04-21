package com.newrelic.agent.android.metric;

public enum MetricUnit {
    PERCENT("%"),
    BYTES("bytes"),
    SECONDS("sec"),
    BYTES_PER_SECOND("bytes/second"),
    OPERATIONS("op");
    
    private String label;

    private MetricUnit(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
