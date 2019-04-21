package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.MethodMeasurement;
import com.newrelic.agent.android.tracing.Trace;

public class MethodMeasurementProducer extends BaseMeasurementProducer {
    public MethodMeasurementProducer() {
        super(MeasurementType.Method);
    }

    public void produceMeasurement(Trace trace) {
        produceMeasurement(new MethodMeasurement(trace.displayName, trace.scope, trace.entryTimestamp, trace.exitTimestamp, trace.exclusiveTime, trace.getCategory()));
    }
}
