package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HttpError;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.http.HttpErrorMeasurement;

public class HttpErrorHarvestingConsumer extends BaseMeasurementConsumer {
    public HttpErrorHarvestingConsumer() {
        super(MeasurementType.HttpError);
    }

    public void consumeMeasurement(Measurement measurement) {
        Harvest.addHttpErrorTrace(new HttpError((HttpErrorMeasurement) measurement));
    }
}
