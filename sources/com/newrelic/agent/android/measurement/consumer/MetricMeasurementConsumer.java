package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestLifecycleAware;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.metric.MetricStore;

public abstract class MetricMeasurementConsumer extends BaseMeasurementConsumer implements HarvestLifecycleAware {
    protected MetricStore metrics = new MetricStore();
    protected boolean recordUnscopedMetrics = true;

    public abstract String formatMetricName(String str);

    public MetricMeasurementConsumer(MeasurementType measurementType) {
        super(measurementType);
        Harvest.addHarvestListener(this);
    }

    public void consumeMeasurement(Measurement measurement) {
        String name = formatMetricName(measurement.getName());
        String scope = measurement.getScope();
        double delta = measurement.getEndTimeInSeconds() - measurement.getStartTimeInSeconds();
        if (scope != null) {
            Metric scopedMetric = this.metrics.get(name, scope);
            if (scopedMetric == null) {
                scopedMetric = new Metric(name, scope);
                this.metrics.add(scopedMetric);
            }
            scopedMetric.sample(delta);
            scopedMetric.addExclusive(measurement.getExclusiveTimeInSeconds());
        }
        if (this.recordUnscopedMetrics) {
            Metric unscopedMetric = this.metrics.get(name);
            if (unscopedMetric == null) {
                unscopedMetric = new Metric(name);
                this.metrics.add(unscopedMetric);
            }
            unscopedMetric.sample(delta);
            unscopedMetric.addExclusive(measurement.getExclusiveTimeInSeconds());
        }
    }

    /* Access modifiers changed, original: protected */
    public void addMetric(Metric newMetric) {
        Metric metric;
        if (newMetric.getScope() != null) {
            metric = this.metrics.get(newMetric.getName(), newMetric.getScope());
        } else {
            metric = this.metrics.get(newMetric.getName());
        }
        if (metric != null) {
            metric.aggregate(newMetric);
        } else {
            this.metrics.add(newMetric);
        }
    }

    public void onHarvest() {
        for (Metric metric : this.metrics.getAll()) {
            Harvest.addMetric(metric);
        }
    }

    public void onHarvestComplete() {
        this.metrics.clear();
    }

    public void onHarvestError() {
        this.metrics.clear();
    }

    public void onHarvestSendFailed() {
        this.metrics.clear();
    }
}
