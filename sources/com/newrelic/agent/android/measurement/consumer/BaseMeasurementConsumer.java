package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import java.util.Collection;

public class BaseMeasurementConsumer extends HarvestAdapter implements MeasurementConsumer {
    private final MeasurementType measurementType;

    public BaseMeasurementConsumer(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public MeasurementType getMeasurementType() {
        return this.measurementType;
    }

    public void consumeMeasurement(Measurement measurement) {
    }

    public void consumeMeasurements(Collection<Measurement> measurements) {
        for (Measurement measurement : measurements) {
            consumeMeasurement(measurement);
        }
    }
}
