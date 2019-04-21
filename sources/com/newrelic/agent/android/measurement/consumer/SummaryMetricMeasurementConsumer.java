package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.instrumentation.MetricCategory;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.BaseMeasurement;
import com.newrelic.agent.android.measurement.CustomMetricMeasurement;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.MethodMeasurement;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceLifecycleAware;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class SummaryMetricMeasurementConsumer extends MetricMeasurementConsumer implements TraceLifecycleAware {
    private static final String ACTIVITY_METRIC_PREFIX = "Mobile/Activity/Summary/Name/";
    private static final String METRIC_PREFIX = "Mobile/Summary/";
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final List<ActivityTrace> completedTraces;

    public SummaryMetricMeasurementConsumer() {
        super(MeasurementType.Any);
        this.completedTraces = new CopyOnWriteArrayList();
        this.recordUnscopedMetrics = false;
        TraceMachine.addTraceListener(this);
    }

    public void consumeMeasurement(Measurement measurement) {
        if (measurement != null) {
            switch (measurement.getType()) {
                case Method:
                    consumeMethodMeasurement((MethodMeasurement) measurement);
                    return;
                case Network:
                    consumeNetworkMeasurement((HttpTransactionMeasurement) measurement);
                    return;
                case Custom:
                    consumeCustomMeasurement((CustomMetricMeasurement) measurement);
                    return;
                default:
                    return;
            }
        }
    }

    private void consumeMethodMeasurement(MethodMeasurement methodMeasurement) {
        if (methodMeasurement.getCategory() == null || methodMeasurement.getCategory() == MetricCategory.NONE) {
            methodMeasurement.setCategory(MetricCategory.categoryForMethod(methodMeasurement.getName()));
            if (methodMeasurement.getCategory() == MetricCategory.NONE) {
                return;
            }
        }
        BaseMeasurement summary = new BaseMeasurement((Measurement) methodMeasurement);
        summary.setName(methodMeasurement.getCategory().getCategoryName());
        super.consumeMeasurement(summary);
    }

    private void consumeCustomMeasurement(CustomMetricMeasurement customMetricMeasurement) {
        if (customMetricMeasurement.getCategory() != null && customMetricMeasurement.getCategory() != MetricCategory.NONE) {
            BaseMeasurement summary = new BaseMeasurement((Measurement) customMetricMeasurement);
            summary.setName(customMetricMeasurement.getCategory().getCategoryName());
            super.consumeMeasurement(summary);
        }
    }

    private void consumeNetworkMeasurement(HttpTransactionMeasurement networkMeasurement) {
        BaseMeasurement summary = new BaseMeasurement((Measurement) networkMeasurement);
        summary.setName(MetricCategory.NETWORK.getCategoryName());
        super.consumeMeasurement(summary);
    }

    /* Access modifiers changed, original: protected */
    public String formatMetricName(String name) {
        return METRIC_PREFIX + name.replace("#", "/");
    }

    public void onHarvest() {
        if (this.metrics.getAll().size() != 0 && this.completedTraces.size() != 0) {
            for (ActivityTrace trace : this.completedTraces) {
                summarizeActivityMetrics(trace);
            }
            if (this.metrics.getAll().size() != 0) {
                log.debug("Not all metrics were summarized!");
            }
            this.completedTraces.clear();
        }
    }

    private void summarizeActivityNetworkMetrics(ActivityTrace activityTrace) {
        String activityName = activityTrace.getActivityName();
        if (activityTrace.networkCountMetric.getCount() > 0) {
            activityTrace.networkCountMetric.setName(activityTrace.networkCountMetric.getName().replace("<activity>", activityName));
            activityTrace.networkCountMetric.setCount(1);
            activityTrace.networkCountMetric.setMinFieldValue(Double.valueOf(activityTrace.networkCountMetric.getTotal()));
            activityTrace.networkCountMetric.setMaxFieldValue(Double.valueOf(activityTrace.networkCountMetric.getTotal()));
            Harvest.addMetric(activityTrace.networkCountMetric);
        }
        if (activityTrace.networkTimeMetric.getCount() > 0) {
            activityTrace.networkTimeMetric.setName(activityTrace.networkTimeMetric.getName().replace("<activity>", activityName));
            activityTrace.networkTimeMetric.setCount(1);
            activityTrace.networkTimeMetric.setMinFieldValue(Double.valueOf(activityTrace.networkTimeMetric.getTotal()));
            activityTrace.networkTimeMetric.setMaxFieldValue(Double.valueOf(activityTrace.networkTimeMetric.getTotal()));
            Harvest.addMetric(activityTrace.networkTimeMetric);
        }
    }

    private void summarizeActivityMetrics(ActivityTrace activityTrace) {
        Trace trace = activityTrace.rootTrace;
        List<Metric> activityMetrics = this.metrics.removeAllWithScope(trace.metricName);
        List<Metric> backgroundMetrics = this.metrics.removeAllWithScope(trace.metricBackgroundName);
        Map<String, Metric> summaryMetrics = new HashMap();
        for (Metric activityMetric : activityMetrics) {
            summaryMetrics.put(activityMetric.getName(), activityMetric);
        }
        for (Metric backgroundMetric : backgroundMetrics) {
            if (summaryMetrics.containsKey(backgroundMetric.getName())) {
                ((Metric) summaryMetrics.get(backgroundMetric.getName())).aggregate(backgroundMetric);
            } else {
                summaryMetrics.put(backgroundMetric.getName(), backgroundMetric);
            }
        }
        double totalExclusiveTime = 0.0d;
        for (Metric metric : summaryMetrics.values()) {
            totalExclusiveTime += metric.getExclusive();
        }
        double traceTime = ((double) (trace.exitTimestamp - trace.entryTimestamp)) / 1000.0d;
        for (Metric metric2 : summaryMetrics.values()) {
            double normalizedTime = 0.0d;
            if (!(metric2.getExclusive() == 0.0d || totalExclusiveTime == 0.0d)) {
                normalizedTime = metric2.getExclusive() / totalExclusiveTime;
            }
            double scaledTime = normalizedTime * traceTime;
            metric2.setTotal(Double.valueOf(scaledTime));
            metric2.setExclusive(Double.valueOf(scaledTime));
            metric2.setMinFieldValue(Double.valueOf(0.0d));
            metric2.setMaxFieldValue(Double.valueOf(0.0d));
            metric2.setSumOfSquares(Double.valueOf(0.0d));
            metric2.setScope(ACTIVITY_METRIC_PREFIX + trace.displayName);
            Harvest.addMetric(metric2);
            Metric unScoped = new Metric(metric2);
            unScoped.setScope(null);
            Harvest.addMetric(unScoped);
        }
        summarizeActivityNetworkMetrics(activityTrace);
    }

    public void onHarvestError() {
    }

    public void onHarvestComplete() {
    }

    public void onTraceStart(ActivityTrace activityTrace) {
    }

    public void onTraceComplete(ActivityTrace activityTrace) {
        if (!this.completedTraces.contains(activityTrace)) {
            this.completedTraces.add(activityTrace);
        }
    }

    public void onEnterMethod() {
    }

    public void onExitMethod() {
    }

    public void onTraceRename(ActivityTrace activityTrace) {
    }
}
