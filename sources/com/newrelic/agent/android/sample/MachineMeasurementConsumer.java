package com.newrelic.agent.android.sample;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.consumer.MetricMeasurementConsumer;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.Sample;

public class MachineMeasurementConsumer extends MetricMeasurementConsumer {
    public MachineMeasurementConsumer() {
        super(MeasurementType.Machine);
    }

    /* Access modifiers changed, original: protected */
    public String formatMetricName(String name) {
        return name;
    }

    public void consumeMeasurement(Measurement measurement) {
    }

    public void onHarvest() {
        Sample memorySample = Sampler.sampleMemory();
        if (memorySample != null) {
            Metric memoryMetric = new Metric("Memory/Used");
            memoryMetric.sample(memorySample.getValue().doubleValue());
            addMetric(memoryMetric);
        }
        super.onHarvest();
    }
}
