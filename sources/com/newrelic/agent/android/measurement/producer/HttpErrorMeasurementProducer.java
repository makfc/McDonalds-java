package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.ThreadInfo;
import com.newrelic.agent.android.measurement.http.HttpErrorMeasurement;
import com.newrelic.agent.android.util.Util;
import java.util.HashMap;
import java.util.Map;

public class HttpErrorMeasurementProducer extends BaseMeasurementProducer {
    public static final String HTTP_METHOD_PARAMS_KEY = "http_method";
    public static final String WAN_TYPE_PARAMS_KEY = "wan_type";
    private static final AgentLog log = AgentLogManager.getAgentLog();

    public HttpErrorMeasurementProducer() {
        super(MeasurementType.HttpError);
    }

    public void produceMeasurement(String url, String httpMethod, int statusCode) {
        produceMeasurement(url, httpMethod, statusCode, "");
    }

    public void produceMeasurement(String url, String httpMethod, int statusCode, String responseBody) {
        produceMeasurement(url, httpMethod, statusCode, responseBody, null);
    }

    public void produceMeasurement(String url, String httpMethod, int statusCode, String responseBody, Map<String, String> params) {
        produceMeasurement(url, httpMethod, statusCode, responseBody, params, new ThreadInfo());
    }

    public void produceMeasurement(String urlString, String httpMethod, int statusCode, String responseBody, Map<String, String> params, ThreadInfo threadInfo) {
        produceMeasurement(urlString, httpMethod, statusCode, 0, responseBody, params, threadInfo);
    }

    public void produceMeasurement(String urlString, String httpMethod, int statusCode, int errorCode, String responseBody, Map<String, String> params, ThreadInfo threadInfo) {
        String url = Util.sanitizeUrl(urlString);
        if (url != null) {
            HttpErrorMeasurement measurement = new HttpErrorMeasurement(url, statusCode);
            if (params == null) {
                params = new HashMap();
            }
            params.put(HTTP_METHOD_PARAMS_KEY, httpMethod);
            params.put(WAN_TYPE_PARAMS_KEY, Agent.getActiveNetworkWanType());
            measurement.setThreadInfo(threadInfo);
            measurement.setStackTrace(getSanitizedStackTrace());
            measurement.setResponseBody(responseBody);
            measurement.setErrorCode(errorCode);
            measurement.setParams(params);
            measurement.setWanType(Agent.getActiveNetworkWanType());
            measurement.setHttpMethod(httpMethod);
            produceMeasurement(measurement);
        }
    }

    private String getSanitizedStackTrace() {
        StringBuilder stackTrace = new StringBuilder();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int numErrors = 0;
        for (int i = 0; i < stackTraceElements.length; i++) {
            StackTraceElement frame = stackTraceElements[i];
            if (!shouldFilterStackTraceElement(frame)) {
                stackTrace.append(frame.toString());
                if (i <= stackTraceElements.length - 1) {
                    stackTrace.append("\n");
                }
                numErrors++;
                if (numErrors >= Agent.getStackTraceLimit()) {
                    break;
                }
            }
        }
        return stackTrace.toString();
    }

    private boolean shouldFilterStackTraceElement(StackTraceElement element) {
        String className = element.getClassName();
        String method = element.getMethodName();
        if (className.startsWith("com.newrelic")) {
            return true;
        }
        if (className.startsWith("dalvik.system.VMStack") && method.startsWith("getThreadStackTrace")) {
            return true;
        }
        if (className.startsWith("java.lang.Thread") && method.startsWith("getStackTrace")) {
            return true;
        }
        return false;
    }
}
