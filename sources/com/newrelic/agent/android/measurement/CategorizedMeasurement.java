package com.newrelic.agent.android.measurement;

import com.newrelic.agent.android.instrumentation.MetricCategory;

public class CategorizedMeasurement extends BaseMeasurement {
    private MetricCategory category;

    public CategorizedMeasurement(MeasurementType measurementType) {
        super(measurementType);
    }

    public MetricCategory getCategory() {
        return this.category;
    }

    public void setCategory(MetricCategory category) {
        this.category = category;
    }
}
