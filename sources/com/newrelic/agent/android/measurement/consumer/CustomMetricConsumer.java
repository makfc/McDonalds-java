package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.measurement.CustomMetricMeasurement;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.metric.Metric;

public class CustomMetricConsumer extends MetricMeasurementConsumer {
    private static final String METRIC_PREFIX = "Custom/";

    public CustomMetricConsumer() {
        super(MeasurementType.Custom);
    }

    /* Access modifiers changed, original: protected */
    public String formatMetricName(String name) {
        return METRIC_PREFIX + name;
    }

    public void consumeMeasurement(Measurement measurement) {
        Metric metric = ((CustomMetricMeasurement) measurement).getCustomMetric();
        metric.setName(formatMetricName(metric.getName()));
        addMetric(metric);
    }
}
