package com.newrelic.agent.android.util;

import com.newrelic.agent.android.harvest.AgentHealth;
import com.newrelic.agent.android.harvest.AgentHealthException;
import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;

public class ExceptionHelper implements HarvestErrorCodes {
    private static final AgentLog log = AgentLogManager.getAgentLog();

    public static int exceptionToErrorCode(Exception e) {
        log.debug("ExceptionHelper: exception " + e.getClass().getName() + " to error code.");
        if (e instanceof ClientProtocolException) {
            return HarvestErrorCodes.NSURLErrorBadServerResponse;
        }
        if (e instanceof UnknownHostException) {
            return HarvestErrorCodes.NSURLErrorDNSLookupFailed;
        }
        if (e instanceof NoRouteToHostException) {
            return HarvestErrorCodes.NSURLErrorCannotFindHost;
        }
        if (e instanceof PortUnreachableException) {
            return HarvestErrorCodes.NSURLErrorCannotFindHost;
        }
        if (e instanceof SocketTimeoutException) {
            return HarvestErrorCodes.NSURLErrorTimedOut;
        }
        if (e instanceof ConnectTimeoutException) {
            return HarvestErrorCodes.NSURLErrorTimedOut;
        }
        if (e instanceof ConnectException) {
            return HarvestErrorCodes.NSURLErrorCannotConnectToHost;
        }
        if (e instanceof MalformedURLException) {
            return -1000;
        }
        if (e instanceof SSLException) {
            return HarvestErrorCodes.NSURLErrorSecureConnectionFailed;
        }
        if (e instanceof FileNotFoundException) {
            return HarvestErrorCodes.NRURLErrorFileDoesNotExist;
        }
        if (e instanceof EOFException) {
            return HarvestErrorCodes.NSURLErrorRequestBodyStreamExhausted;
        }
        if (e instanceof IOException) {
            recordSupportabilityMetric(e, "IOException");
            return -1;
        } else if (!(e instanceof RuntimeException)) {
            return -1;
        } else {
            recordSupportabilityMetric(e, "RuntimeException");
            return -1;
        }
    }

    public static void recordSupportabilityMetric(Exception e, String baseExceptionKey) {
        AgentHealthException agentHealthException = new AgentHealthException(e);
        StackTraceElement topTraceElement = agentHealthException.getStackTrace()[0];
        log.error(String.format("ExceptionHelper: %s:%s(%s:%s) %s[%s] %s", new Object[]{agentHealthException.getSourceClass(), agentHealthException.getSourceMethod(), topTraceElement.getFileName(), Integer.valueOf(topTraceElement.getLineNumber()), baseExceptionKey, agentHealthException.getExceptionClass(), agentHealthException.getMessage()}));
        AgentHealth.noticeException(agentHealthException, baseExceptionKey);
    }
}
