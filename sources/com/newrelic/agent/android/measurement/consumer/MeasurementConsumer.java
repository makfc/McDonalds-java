package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import java.util.Collection;

public interface MeasurementConsumer {
    void consumeMeasurement(Measurement measurement);

    void consumeMeasurements(Collection<Measurement> collection);

    MeasurementType getMeasurementType();
}
