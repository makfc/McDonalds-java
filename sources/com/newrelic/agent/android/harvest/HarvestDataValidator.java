package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.List;

public class HarvestDataValidator extends HarvestAdapter {
    public void onHarvestFinalize() {
        if (Harvest.isInitialized()) {
            ensureActivityNameMetricsExist();
        }
    }

    public void ensureActivityNameMetricsExist() {
        HarvestData harvestData = Harvest.getInstance().getHarvestData();
        ActivityTraces activityTraces = harvestData.getActivityTraces();
        if (activityTraces != null && activityTraces.count() != 0) {
            MachineMeasurements metrics = harvestData.getMetrics();
            if (metrics != null && !metrics.isEmpty()) {
                for (ActivityTrace activityTrace : activityTraces.getActivityTraces()) {
                    String activityName = activityTrace.rootTrace.displayName;
                    int hashIndex = activityName.indexOf("#");
                    if (hashIndex > 0) {
                        activityName = activityName.substring(0, hashIndex);
                    }
                    String activityMetricRoot = TraceMachine.ACTIVITY_METRIC_PREFIX + activityName;
                    boolean foundMetricForActivity = false;
                    List<Metric> unScopedMetrics = metrics.getMetrics().getAllUnscoped();
                    if (unScopedMetrics != null && unScopedMetrics.size() > 0) {
                        for (Metric metric : unScopedMetrics) {
                            if (metric.getName().startsWith(activityMetricRoot)) {
                                foundMetricForActivity = true;
                                break;
                            }
                        }
                    }
                    if (!foundMetricForActivity) {
                        metrics.addMetric(new Metric(activityMetricRoot));
                    }
                }
            }
        }
    }
}
