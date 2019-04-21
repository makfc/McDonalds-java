package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.measurement.MeasurementType;

public class MethodMeasurementConsumer extends MetricMeasurementConsumer {
    private static final String METRIC_PREFIX = "Method/";

    public MethodMeasurementConsumer() {
        super(MeasurementType.Method);
    }

    /* Access modifiers changed, original: protected */
    public String formatMetricName(String name) {
        return METRIC_PREFIX + name.replace("#", "/");
    }
}
