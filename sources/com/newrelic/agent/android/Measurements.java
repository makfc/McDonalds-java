package com.newrelic.agent.android;

import com.newrelic.agent.android.activity.MeasuredActivity;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.ThreadInfo;
import com.newrelic.agent.android.measurement.consumer.ActivityMeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.CustomMetricConsumer;
import com.newrelic.agent.android.measurement.consumer.HttpErrorHarvestingConsumer;
import com.newrelic.agent.android.measurement.consumer.HttpTransactionHarvestingConsumer;
import com.newrelic.agent.android.measurement.consumer.MeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.MethodMeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.SummaryMetricMeasurementConsumer;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.measurement.producer.ActivityMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.CustomMetricProducer;
import com.newrelic.agent.android.measurement.producer.HttpErrorMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MethodMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.NetworkMeasurementProducer;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.tracing.Trace;
import java.util.Map;

public class Measurements {
    private static final ActivityMeasurementConsumer activityConsumer = new ActivityMeasurementConsumer();
    private static final ActivityMeasurementProducer activityMeasurementProducer = new ActivityMeasurementProducer();
    private static boolean broadcastNewMeasurements = true;
    private static final CustomMetricConsumer customMetricConsumer = new CustomMetricConsumer();
    private static final CustomMetricProducer customMetricProducer = new CustomMetricProducer();
    private static final HttpErrorHarvestingConsumer httpErrorHarvester = new HttpErrorHarvestingConsumer();
    private static final HttpErrorMeasurementProducer httpErrorMeasurementProducer = new HttpErrorMeasurementProducer();
    private static final HttpTransactionHarvestingConsumer httpTransactionHarvester = new HttpTransactionHarvestingConsumer();
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private static final MeasurementEngine measurementEngine = new MeasurementEngine();
    private static final MethodMeasurementConsumer methodMeasurementConsumer = new MethodMeasurementConsumer();
    private static final MethodMeasurementProducer methodMeasurementProducer = new MethodMeasurementProducer();
    private static final NetworkMeasurementProducer networkMeasurementProducer = new NetworkMeasurementProducer();
    private static final SummaryMetricMeasurementConsumer summaryMetricMeasurementConsumer = new SummaryMetricMeasurementConsumer();

    public static void initialize() {
        log.info("Measurement Engine initialized.");
        TaskQueue.start();
        addMeasurementProducer(httpErrorMeasurementProducer);
        addMeasurementProducer(networkMeasurementProducer);
        addMeasurementProducer(activityMeasurementProducer);
        addMeasurementProducer(methodMeasurementProducer);
        addMeasurementProducer(customMetricProducer);
        addMeasurementConsumer(httpErrorHarvester);
        addMeasurementConsumer(httpTransactionHarvester);
        addMeasurementConsumer(activityConsumer);
        addMeasurementConsumer(methodMeasurementConsumer);
        addMeasurementConsumer(summaryMetricMeasurementConsumer);
        addMeasurementConsumer(customMetricConsumer);
    }

    public static void shutdown() {
        TaskQueue.stop();
        measurementEngine.clear();
        log.info("Measurement Engine shutting down.");
        removeMeasurementProducer(httpErrorMeasurementProducer);
        removeMeasurementProducer(networkMeasurementProducer);
        removeMeasurementProducer(activityMeasurementProducer);
        removeMeasurementProducer(methodMeasurementProducer);
        removeMeasurementProducer(customMetricProducer);
        removeMeasurementConsumer(httpErrorHarvester);
        removeMeasurementConsumer(httpTransactionHarvester);
        removeMeasurementConsumer(activityConsumer);
        removeMeasurementConsumer(methodMeasurementConsumer);
        removeMeasurementConsumer(summaryMetricMeasurementConsumer);
        removeMeasurementConsumer(customMetricConsumer);
    }

    public static void addHttpError(String url, String httpMethod, int statusCode) {
        if (!Harvest.isDisabled()) {
            httpErrorMeasurementProducer.produceMeasurement(url, httpMethod, statusCode);
            newMeasurementBroadcast();
        }
    }

    public static void addHttpError(String url, String httpMethod, int statusCode, String responseBody) {
        if (!Harvest.isDisabled()) {
            httpErrorMeasurementProducer.produceMeasurement(url, httpMethod, statusCode, responseBody);
            newMeasurementBroadcast();
        }
    }

    public static void addHttpError(String url, String httpMethod, int statusCode, String responseBody, Map<String, String> params) {
        if (!Harvest.isDisabled()) {
            httpErrorMeasurementProducer.produceMeasurement(url, httpMethod, statusCode, responseBody, params);
            newMeasurementBroadcast();
        }
    }

    public static void addHttpError(String url, String httpMethod, int statusCode, int errorCode, String responseBody, Map<String, String> params) {
        if (!Harvest.isDisabled()) {
            httpErrorMeasurementProducer.produceMeasurement(url, httpMethod, statusCode, errorCode, responseBody, params, new ThreadInfo());
            newMeasurementBroadcast();
        }
    }

    public static void addHttpError(String url, String httpMethod, int statusCode, String responseBody, Map<String, String> params, ThreadInfo threadInfo) {
        if (!Harvest.isDisabled()) {
            httpErrorMeasurementProducer.produceMeasurement(url, httpMethod, statusCode, responseBody, params, threadInfo);
            newMeasurementBroadcast();
        }
    }

    public static void addHttpTransaction(HttpTransactionMeasurement transactionMeasurement) {
        if (!Harvest.isDisabled()) {
            if (transactionMeasurement == null) {
                log.error("TransactionMeasurement is null. HttpTransactionMeasurement measurement not created.");
                return;
            }
            networkMeasurementProducer.produceMeasurement(transactionMeasurement);
            newMeasurementBroadcast();
        }
    }

    public static void addHttpError(TransactionData transactionData, String responseBody, Map<String, String> params) {
        if (transactionData == null) {
            log.error("TransactionData is null. HttpError measurement not created.");
        } else {
            addHttpError(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), responseBody, (Map) params);
        }
    }

    public static void addCustomMetric(String name, String category, int count, double totalValue, double exclusiveValue) {
        if (!Harvest.isDisabled()) {
            customMetricProducer.produceMeasurement(name, category, count, totalValue, exclusiveValue);
            newMeasurementBroadcast();
        }
    }

    public static void addCustomMetric(String name, String category, int count, double totalValue, double exclusiveValue, MetricUnit countUnit, MetricUnit valueUnit) {
        if (!Harvest.isDisabled()) {
            customMetricProducer.produceMeasurement(name, category, count, totalValue, exclusiveValue, countUnit, valueUnit);
            newMeasurementBroadcast();
        }
    }

    public static void setBroadcastNewMeasurements(boolean broadcast) {
        broadcastNewMeasurements = broadcast;
    }

    private static void newMeasurementBroadcast() {
        if (broadcastNewMeasurements) {
            broadcast();
        }
    }

    public static void broadcast() {
        measurementEngine.broadcastMeasurements();
    }

    public static MeasuredActivity startActivity(String activityName) {
        if (Harvest.isDisabled()) {
            return null;
        }
        return measurementEngine.startActivity(activityName);
    }

    public static void renameActivity(String oldName, String newName) {
        measurementEngine.renameActivity(oldName, newName);
    }

    public static void endActivity(String activityName) {
        if (!Harvest.isDisabled()) {
            activityMeasurementProducer.produceMeasurement(measurementEngine.endActivity(activityName));
            newMeasurementBroadcast();
        }
    }

    public static void endActivity(MeasuredActivity activity) {
        if (!Harvest.isDisabled()) {
            measurementEngine.endActivity(activity);
            activityMeasurementProducer.produceMeasurement(activity);
            newMeasurementBroadcast();
        }
    }

    public static void endActivityWithoutMeasurement(MeasuredActivity activity) {
        if (!Harvest.isDisabled()) {
            measurementEngine.endActivity(activity);
        }
    }

    public static void addTracedMethod(Trace trace) {
        if (!Harvest.isDisabled()) {
            methodMeasurementProducer.produceMeasurement(trace);
            newMeasurementBroadcast();
        }
    }

    public static void addMeasurementProducer(MeasurementProducer measurementProducer) {
        measurementEngine.addMeasurementProducer(measurementProducer);
    }

    public static void removeMeasurementProducer(MeasurementProducer measurementProducer) {
        measurementEngine.removeMeasurementProducer(measurementProducer);
    }

    public static void addMeasurementConsumer(MeasurementConsumer measurementConsumer) {
        measurementEngine.addMeasurementConsumer(measurementConsumer);
    }

    public static void removeMeasurementConsumer(MeasurementConsumer measurementConsumer) {
        measurementEngine.removeMeasurementConsumer(measurementConsumer);
    }

    public static void process() {
        measurementEngine.broadcastMeasurements();
    }
}
