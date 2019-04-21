package com.newrelic.agent.android;

import android.content.Context;
import android.text.TextUtils;
import com.newrelic.agent.android.agentdata.AgentDataController;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticsControllerImpl;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.logging.AndroidAgentLog;
import com.newrelic.agent.android.logging.NullAgentLog;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.tracing.TracingInactiveException;
import com.newrelic.agent.android.util.NetworkFailure;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

/* renamed from: com.newrelic.agent.android.NewRelic */
public final class C2623NewRelic {
    private static final String UNKNOWN_HTTP_REQUEST_TYPE = "unknown";
    protected static final AgentConfiguration agentConfiguration = new AgentConfiguration();
    protected static final AgentLog log = AgentLogManager.getAgentLog();
    protected static boolean started = false;
    protected int logLevel = 3;
    protected boolean loggingEnabled = true;

    private boolean isInstrumented() {
        return true;
    }

    protected C2623NewRelic(String token) {
        agentConfiguration.setApplicationToken(token);
    }

    public static C2623NewRelic withApplicationToken(String token) {
        return new C2623NewRelic(token);
    }

    public C2623NewRelic usingSsl(boolean useSsl) {
        agentConfiguration.setUseSsl(useSsl);
        return this;
    }

    public C2623NewRelic usingCollectorAddress(String address) {
        agentConfiguration.setCollectorHost(address);
        return this;
    }

    public C2623NewRelic usingCrashCollectorAddress(String address) {
        agentConfiguration.setCrashCollectorHost(address);
        return this;
    }

    public C2623NewRelic withLocationServiceEnabled(boolean enabled) {
        agentConfiguration.setUseLocationService(enabled);
        return this;
    }

    public C2623NewRelic withLoggingEnabled(boolean enabled) {
        this.loggingEnabled = enabled;
        return this;
    }

    public C2623NewRelic withLogLevel(int level) {
        this.logLevel = level;
        return this;
    }

    public C2623NewRelic withCrashReportingEnabled(boolean enabled) {
        agentConfiguration.setReportCrashes(enabled);
        if (enabled) {
            C2623NewRelic.enableFeature(FeatureFlag.CrashReporting);
        } else {
            C2623NewRelic.disableFeature(FeatureFlag.CrashReporting);
        }
        return this;
    }

    public C2623NewRelic withHttpResponseBodyCaptureEnabled(boolean enabled) {
        if (enabled) {
            C2623NewRelic.enableFeature(FeatureFlag.HttpResponseBodyCapture);
        } else {
            C2623NewRelic.disableFeature(FeatureFlag.HttpResponseBodyCapture);
        }
        return this;
    }

    public C2623NewRelic withApplicationVersion(String appVersion) {
        if (appVersion != null) {
            agentConfiguration.setCustomApplicationVersion(appVersion);
        }
        return this;
    }

    public C2623NewRelic withApplicationFramework(ApplicationPlatform applicationPlatform) {
        if (applicationPlatform != null) {
            agentConfiguration.setApplicationPlatform(applicationPlatform);
        }
        return this;
    }

    @Deprecated
    public C2623NewRelic withAnalyticsEvents(boolean enabled) {
        C2623NewRelic.enableFeature(FeatureFlag.AnalyticsEvents);
        return this;
    }

    public C2623NewRelic withInteractionTracing(boolean enabled) {
        if (enabled) {
            C2623NewRelic.enableFeature(FeatureFlag.InteractionTracing);
        } else {
            C2623NewRelic.disableFeature(FeatureFlag.InteractionTracing);
        }
        return this;
    }

    public C2623NewRelic withDefaultInteractions(boolean enabled) {
        if (enabled) {
            C2623NewRelic.enableFeature(FeatureFlag.DefaultInteractions);
        } else {
            C2623NewRelic.disableFeature(FeatureFlag.DefaultInteractions);
        }
        return this;
    }

    public static void enableFeature(FeatureFlag featureFlag) {
        FeatureFlag.enableFeature(featureFlag);
    }

    public static void disableFeature(FeatureFlag featureFlag) {
        log.debug("Disable feature: " + featureFlag.name());
        FeatureFlag.disableFeature(featureFlag);
    }

    @Deprecated
    public C2623NewRelic withBuildIdentifier(String buildId) {
        StatsEngine.get().inc("Supportability/AgentHealth/Deprecated/WithBuildIdentifier");
        return withApplicationBuild(buildId);
    }

    public C2623NewRelic withApplicationBuild(String buildId) {
        if (!TextUtils.isEmpty(buildId)) {
            agentConfiguration.setCustomBuildIdentifier(buildId);
        }
        return this;
    }

    public void start(Context context) {
        if (started) {
            log.debug("NewRelic is already running.");
            return;
        }
        try {
            AgentLogManager.setAgentLog(this.loggingEnabled ? new AndroidAgentLog() : new NullAgentLog());
            log.setLevel(this.logLevel);
            if (isInstrumented()) {
                AndroidAgentImpl.init(context, agentConfiguration);
                started = true;
                return;
            }
            log.error("Failed to detect New Relic instrumentation.  Something likely went wrong during your build process and you should visit http://support.newrelic.com.");
        } catch (Throwable e) {
            log.error("Error occurred while starting the New Relic agent!", e);
        }
    }

    public static boolean isStarted() {
        return started;
    }

    @Deprecated
    public static void shutdown() {
        StatsEngine.get().inc("Supportability/AgentHealth/Deprecated/Shutdown");
        if (started) {
            try {
                Agent.getImpl().stop();
            } finally {
                Agent.setImpl(NullAgentImpl.instance);
                started = false;
            }
        }
    }

    public static String startInteraction(String actionName) {
        C2623NewRelic.checkNull(actionName, "startInteraction: actionName must be an action/method name.");
        log.debug("NewRelic.startInteraction invoked. actionName: " + actionName);
        TraceMachine.startTracing(actionName.replace("/", "."), true, FeatureFlag.featureEnabled(FeatureFlag.InteractionTracing));
        try {
            return TraceMachine.getActivityTrace().getId();
        } catch (TracingInactiveException e) {
            return null;
        }
    }

    @Deprecated
    public static String startInteraction(Context activityContext, String actionName) {
        C2623NewRelic.checkNull(activityContext, "startInteraction: context must be an Activity instance.");
        C2623NewRelic.checkNull(actionName, "startInteraction: actionName must be an action/method name.");
        TraceMachine.startTracing(activityContext.getClass().getSimpleName() + "#" + actionName.replace("/", "."), false, FeatureFlag.featureEnabled(FeatureFlag.InteractionTracing));
        try {
            return TraceMachine.getActivityTrace().getId();
        } catch (TracingInactiveException e) {
            return null;
        }
    }

    @Deprecated
    public static String startInteraction(Context context, String actionName, boolean cancelRunningTrace) {
        if (!TraceMachine.isTracingActive() || cancelRunningTrace) {
            return C2623NewRelic.startInteraction(context, actionName);
        }
        log.warning("startInteraction: An interaction is already being traced, and invalidateActiveTrace is false. This interaction will not be traced.");
        return null;
    }

    public static void endInteraction(String id) {
        log.debug("NewRelic.endInteraction invoked. id: " + id);
        TraceMachine.endTrace(id);
    }

    public static void setInteractionName(String name) {
        TraceMachine.setRootDisplayName(name);
    }

    public static void startMethodTrace(String actionName) {
        C2623NewRelic.checkNull(actionName, "startMethodTrace: actionName must be an action/method name.");
        TraceMachine.enterMethod(actionName);
    }

    public static void endMethodTrace() {
        log.debug("NewRelic.endMethodTrace invoked.");
        TraceMachine.exitMethod();
    }

    public static void recordMetric(String name, String category, int count, double totalValue, double exclusiveValue) {
        C2623NewRelic.recordMetric(name, category, count, totalValue, exclusiveValue, null, null);
    }

    public static void recordMetric(String name, String category, int count, double totalValue, double exclusiveValue, MetricUnit countUnit, MetricUnit valueUnit) {
        log.debug("NewRelic.recordMeric invoked for name " + name + ", category: " + category + ", count: " + count + ", totalValue " + totalValue + ", exclusiveValue: " + exclusiveValue + ", countUnit: " + countUnit + ", valueUnit: " + valueUnit);
        C2623NewRelic.checkNull(category, "recordMetric: category must not be null. If no MetricCategory is applicable, use MetricCategory.NONE.");
        C2623NewRelic.checkEmpty(name, "recordMetric: name must not be empty.");
        if (!C2623NewRelic.checkNegative(count, "recordMetric: count must not be negative.")) {
            Measurements.addCustomMetric(name, category, count, totalValue, exclusiveValue, countUnit, valueUnit);
        }
    }

    public static void recordMetric(String name, String category, double value) {
        C2623NewRelic.recordMetric(name, category, 1, value, value, null, null);
    }

    public static void recordMetric(String name, String category) {
        C2623NewRelic.recordMetric(name, category, 1.0d);
    }

    public static void noticeHttpTransaction(String url, String httpMethod, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived) {
        C2623NewRelic._noticeHttpTransaction(url, httpMethod, statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, null, null, null);
    }

    public static void noticeHttpTransaction(String url, String httpMethod, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody) {
        C2623NewRelic._noticeHttpTransaction(url, httpMethod, statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, null, null);
    }

    public static void noticeHttpTransaction(String url, String httpMethod, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody, Map<String, String> params) {
        C2623NewRelic._noticeHttpTransaction(url, httpMethod, statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, params, null);
    }

    public static void noticeHttpTransaction(String url, String httpMethod, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody, Map<String, String> params, String appData) {
        C2623NewRelic._noticeHttpTransaction(url, httpMethod, statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, params, appData);
    }

    public static void noticeHttpTransaction(String url, String httpMethod, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody, Map<String, String> params, HttpResponse httpResponse) {
        if (httpResponse != null) {
            Header header = httpResponse.getFirstHeader(TransactionStateUtil.CROSS_PROCESS_ID_HEADER);
            if (!(header == null || header.getValue() == null || header.getValue().length() <= 0)) {
                C2623NewRelic._noticeHttpTransaction(url, httpMethod, statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, params, header.getValue());
                return;
            }
        }
        C2623NewRelic._noticeHttpTransaction(url, httpMethod, statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, params, null);
    }

    public static void noticeHttpTransaction(String url, String httpMethod, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody, Map<String, String> params, URLConnection urlConnection) {
        if (urlConnection != null) {
            String header = urlConnection.getHeaderField(TransactionStateUtil.CROSS_PROCESS_ID_HEADER);
            if (header != null && header.length() > 0) {
                C2623NewRelic._noticeHttpTransaction(url, httpMethod, statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, params, header);
                return;
            }
        }
        C2623NewRelic._noticeHttpTransaction(url, httpMethod, statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, params, null);
    }

    @Deprecated
    public static void noticeHttpTransaction(String url, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody, Map<String, String> params, HttpResponse httpResponse) {
        C2623NewRelic.noticeHttpTransaction(url, "unknown", statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, (Map) params, httpResponse);
    }

    @Deprecated
    public static void noticeHttpTransaction(String url, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody, Map<String, String> params, URLConnection urlConnection) {
        C2623NewRelic.noticeHttpTransaction(url, "unknown", statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, (Map) params, urlConnection);
    }

    @Deprecated
    public static void noticeHttpTransaction(String url, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived) {
        C2623NewRelic._noticeHttpTransaction(url, "unknown", statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, null, null, null);
    }

    @Deprecated
    public static void noticeHttpTransaction(String url, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody) {
        C2623NewRelic._noticeHttpTransaction(url, "unknown", statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, null, null);
    }

    @Deprecated
    public static void noticeHttpTransaction(String url, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody, Map<String, String> params) {
        C2623NewRelic._noticeHttpTransaction(url, "unknown", statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, params, null);
    }

    @Deprecated
    public static void noticeHttpTransaction(String url, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody, Map<String, String> params, String appData) {
        C2623NewRelic._noticeHttpTransaction(url, "unknown", statusCode, startTimeMs, endTimeMs, bytesSent, bytesReceived, responseBody, params, appData);
    }

    protected static void _noticeHttpTransaction(String url, String httpMethod, int statusCode, long startTimeMs, long endTimeMs, long bytesSent, long bytesReceived, String responseBody, Map<String, String> params, String appData) {
        C2623NewRelic.checkEmpty(url, "noticeHttpTransaction: url must not be empty.");
        C2623NewRelic.checkEmpty(httpMethod, "noticeHttpTransaction: httpMethod must not be empty.");
        try {
            URL url2 = new URL(url);
            double totalTime = (double) (endTimeMs - startTimeMs);
            if (!C2623NewRelic.checkNegative((int) totalTime, "noticeHttpTransaction: the startTimeMs is later than the endTimeMs, resulting in a negative total time.")) {
                TaskQueue.queue(new HttpTransactionMeasurement(url, httpMethod, statusCode, 0, startTimeMs, totalTime / 1000.0d, bytesSent, bytesReceived, appData));
                if (((long) statusCode) >= 400) {
                    Measurements.addHttpError(url, httpMethod, statusCode, responseBody, params);
                }
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("noticeHttpTransaction: URL is malformed: " + url);
        }
    }

    private static void noticeNetworkFailureDelegate(String url, String httpMethod, long startTime, long endTime, NetworkFailure failure, String message) {
        float durationInSeconds = ((float) (endTime - startTime)) / 1000.0f;
        TransactionState ts = new TransactionState();
        TransactionStateUtil.inspectAndInstrument(ts, url, httpMethod);
        ts.setErrorCode(failure.getErrorCode());
        TransactionData transactionData = ts.end();
        Map<String, String> params = new TreeMap();
        params.put("content_length", "0");
        params.put("content_type", "text/html");
        TaskQueue.queue(new HttpTransactionMeasurement(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), startTime, (double) durationInSeconds, transactionData.getBytesSent(), transactionData.getBytesReceived(), transactionData.getAppData()));
        if (ts.getErrorCode() != 0) {
            Measurements.addHttpError(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), transactionData.getErrorCode(), message, (Map) params);
        } else {
            Measurements.addHttpError(transactionData.getUrl(), transactionData.getHttpMethod(), transactionData.getStatusCode(), message, params);
        }
    }

    public static void noticeNetworkFailure(String url, String httpMethod, long startTime, long endTime, NetworkFailure failure, String message) {
        C2623NewRelic.noticeNetworkFailureDelegate(url, httpMethod, startTime, endTime, failure, message);
    }

    public static void noticeNetworkFailure(String url, String httpMethod, long startTime, long endTime, NetworkFailure failure) {
        C2623NewRelic.noticeNetworkFailure(url, httpMethod, startTime, endTime, failure, "");
    }

    public static void noticeNetworkFailure(String url, String httpMethod, long startTime, long endTime, Exception e) {
        C2623NewRelic.checkEmpty(url, "noticeHttpException: url must not be empty.");
        C2623NewRelic.noticeNetworkFailure(url, httpMethod, startTime, endTime, NetworkFailure.exceptionToNetworkFailure(e), e.getMessage());
    }

    @Deprecated
    public static void noticeNetworkFailure(String url, long startTime, long endTime, NetworkFailure failure) {
        C2623NewRelic.noticeNetworkFailure(url, "unknown", startTime, endTime, failure);
    }

    @Deprecated
    public static void noticeNetworkFailure(String url, long startTime, long endTime, Exception e) {
        C2623NewRelic.noticeNetworkFailure(url, "unknown", startTime, endTime, e);
    }

    private static void checkNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    private static void checkEmpty(String string, String message) {
        C2623NewRelic.checkNull(string, message);
        if (string.length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean checkNegative(int number, String message) {
        if (number >= 0) {
            return false;
        }
        log.error(message);
        return true;
    }

    public static void crashNow() {
        C2623NewRelic.crashNow("This is a demonstration crash courtesy of New Relic");
    }

    public static void crashNow(String message) {
        throw new RuntimeException(message);
    }

    public static boolean setAttribute(String name, String value) {
        return AnalyticsControllerImpl.getInstance().setAttribute(name, value);
    }

    public static boolean setAttribute(String name, float value) {
        return AnalyticsControllerImpl.getInstance().setAttribute(name, value);
    }

    public static boolean setAttribute(String name, boolean value) {
        return AnalyticsControllerImpl.getInstance().setAttribute(name, value);
    }

    public static boolean incrementAttribute(String name) {
        return AnalyticsControllerImpl.getInstance().incrementAttribute(name, 1.0f);
    }

    public static boolean incrementAttribute(String name, float value) {
        return AnalyticsControllerImpl.getInstance().incrementAttribute(name, value);
    }

    public static boolean removeAttribute(String name) {
        return AnalyticsControllerImpl.getInstance().removeAttribute(name);
    }

    public static boolean removeAllAttributes() {
        return AnalyticsControllerImpl.getInstance().removeAllAttributes();
    }

    public static boolean setUserId(String userId) {
        return AnalyticsControllerImpl.getInstance().setAttribute(AnalyticAttribute.USER_ID_ATTRIBUTE, userId);
    }

    @Deprecated
    public static boolean recordEvent(String name, Map<String, Object> eventAttributes) {
        if (eventAttributes == null) {
            eventAttributes = new HashMap();
        }
        return AnalyticsControllerImpl.getInstance().recordEvent(name, eventAttributes);
    }

    public static boolean recordCustomEvent(String eventType, Map<String, Object> eventAttributes) {
        if (eventAttributes == null) {
            eventAttributes = new HashMap();
        }
        return AnalyticsControllerImpl.getInstance().recordCustomEvent(eventType, eventAttributes);
    }

    public static boolean recordCustomEvent(String eventType, String eventName, Map<String, Object> eventAttributes) {
        if (eventAttributes == null) {
            eventAttributes = new HashMap();
        }
        if (!(eventName == null || eventName.isEmpty())) {
            eventAttributes.put("name", eventName);
        }
        return C2623NewRelic.recordCustomEvent(eventType, eventAttributes);
    }

    public static boolean recordBreadcrumb(String breadcrumbName) {
        return C2623NewRelic.recordBreadcrumb(breadcrumbName, null);
    }

    public static boolean recordBreadcrumb(String breadcrumbName, Map<String, Object> attributes) {
        if (attributes == null) {
            attributes = new HashMap();
        }
        if (!(breadcrumbName == null || breadcrumbName.isEmpty())) {
            attributes.put("name", breadcrumbName);
        }
        return AnalyticsControllerImpl.getInstance().recordBreadcrumb(breadcrumbName, attributes);
    }

    public static boolean recordHandledException(Exception exceptionToHandle) {
        return C2623NewRelic.recordHandledException(exceptionToHandle, new HashMap());
    }

    public static boolean recordHandledException(Exception exceptionToHandle, Map<String, Object> exceptionAttributes) {
        if (exceptionAttributes == null) {
            exceptionAttributes = new HashMap();
        }
        return AgentDataController.sendAgentData(exceptionToHandle, exceptionAttributes);
    }

    public static void setMaxEventPoolSize(int maxSize) {
        AnalyticsControllerImpl.getInstance().setMaxEventPoolSize(maxSize);
    }

    public static void setMaxEventBufferTime(int maxBufferTimeInSec) {
        AnalyticsControllerImpl.getInstance().setMaxEventBufferTime(maxBufferTimeInSec);
    }

    public static String currentSessionId() {
        return agentConfiguration.getSessionID();
    }
}
