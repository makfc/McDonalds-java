package com.newrelic.agent.android;

import com.newrelic.agent.android.activity.MeasuredActivity;
import com.newrelic.agent.android.activity.NamedActivity;
import com.newrelic.agent.android.measurement.MeasurementException;
import com.newrelic.agent.android.measurement.MeasurementPool;
import com.newrelic.agent.android.measurement.consumer.MeasurementConsumer;
import com.newrelic.agent.android.measurement.producer.MeasurementProducer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MeasurementEngine {
    private final Map<String, MeasuredActivity> activities = new ConcurrentHashMap();
    private final MeasurementPool rootMeasurementPool = new MeasurementPool();

    public MeasuredActivity startActivity(String activityName) {
        if (this.activities.containsKey(activityName)) {
            throw new MeasurementException("An activity with the name '" + activityName + "' has already started.");
        }
        NamedActivity activity = new NamedActivity(activityName);
        this.activities.put(activityName, activity);
        MeasurementPool measurementPool = new MeasurementPool();
        activity.setMeasurementPool(measurementPool);
        this.rootMeasurementPool.addMeasurementConsumer(measurementPool);
        return activity;
    }

    public void renameActivity(String oldName, String newName) {
        MeasuredActivity namedActivity = (MeasuredActivity) this.activities.remove(oldName);
        if (namedActivity != null && (namedActivity instanceof NamedActivity)) {
            this.activities.put(newName, namedActivity);
            ((NamedActivity) namedActivity).rename(newName);
        }
    }

    public MeasuredActivity endActivity(String activityName) {
        MeasuredActivity measuredActivity = (MeasuredActivity) this.activities.get(activityName);
        if (measuredActivity == null) {
            throw new MeasurementException("Activity '" + activityName + "' has not been started.");
        }
        endActivity(measuredActivity);
        return measuredActivity;
    }

    public void endActivity(MeasuredActivity activity) {
        this.rootMeasurementPool.removeMeasurementConsumer(activity.getMeasurementPool());
        this.activities.remove(activity.getName());
        activity.finish();
    }

    public void clear() {
        this.activities.clear();
    }

    public void addMeasurementProducer(MeasurementProducer measurementProducer) {
        this.rootMeasurementPool.addMeasurementProducer(measurementProducer);
    }

    public void removeMeasurementProducer(MeasurementProducer measurementProducer) {
        this.rootMeasurementPool.removeMeasurementProducer(measurementProducer);
    }

    public void addMeasurementConsumer(MeasurementConsumer measurementConsumer) {
        this.rootMeasurementPool.addMeasurementConsumer(measurementConsumer);
    }

    public void removeMeasurementConsumer(MeasurementConsumer measurementConsumer) {
        this.rootMeasurementPool.removeMeasurementConsumer(measurementConsumer);
    }

    public void broadcastMeasurements() {
        this.rootMeasurementPool.broadcastMeasurements();
    }
}
