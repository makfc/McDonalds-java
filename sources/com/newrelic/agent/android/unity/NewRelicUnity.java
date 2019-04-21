package com.newrelic.agent.android.unity;

import com.newrelic.agent.android.C2623NewRelic;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.util.NetworkFailure;
import java.lang.Thread.UncaughtExceptionHandler;

public class NewRelicUnity {
    private static final String ROOT_TRACE_NAME = "Unity";
    private static final AgentLog log = AgentLogManager.getAgentLog();

    static void handleUnityCrash(UnityException ex) {
        UncaughtExceptionHandler currentExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (currentExceptionHandler != null && (currentExceptionHandler instanceof com.newrelic.agent.android.crash.UncaughtExceptionHandler)) {
            currentExceptionHandler.uncaughtException(Thread.currentThread(), ex);
        }
    }

    static boolean recordEvent(UnityEvent event) {
        return C2623NewRelic.recordEvent(event.getName(), event.getAttributes());
    }

    static void noticeNetworkFailure(String url, String httpMethod, long startTime, long endTime, int failureCode, String message) {
        C2623NewRelic.noticeNetworkFailure(url, httpMethod, startTime, endTime, NetworkFailure.fromErrorCode(failureCode), message);
    }
}
