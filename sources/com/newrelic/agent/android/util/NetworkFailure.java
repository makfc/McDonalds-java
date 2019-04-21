package com.newrelic.agent.android.util;

import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;

public enum NetworkFailure {
    Unknown(-1),
    BadURL(-1000),
    TimedOut(HarvestErrorCodes.NSURLErrorTimedOut),
    CannotConnectToHost(HarvestErrorCodes.NSURLErrorCannotConnectToHost),
    DNSLookupFailed(HarvestErrorCodes.NSURLErrorDNSLookupFailed),
    BadServerResponse(HarvestErrorCodes.NSURLErrorBadServerResponse),
    SecureConnectionFailed(HarvestErrorCodes.NSURLErrorSecureConnectionFailed);
    
    private static final AgentLog log = null;
    private int errorCode;

    static {
        log = AgentLogManager.getAgentLog();
    }

    private NetworkFailure(int errorCode) {
        this.errorCode = errorCode;
    }

    public static NetworkFailure exceptionToNetworkFailure(Exception e) {
        log.error("NetworkFailure.exceptionToNetworkFailure: Attempting to convert network exception " + e.getClass().getName() + " to error code.");
        NetworkFailure error = Unknown;
        if (e instanceof UnknownHostException) {
            return DNSLookupFailed;
        }
        if ((e instanceof SocketTimeoutException) || (e instanceof ConnectTimeoutException)) {
            return TimedOut;
        }
        if (e instanceof ConnectException) {
            return CannotConnectToHost;
        }
        if (e instanceof MalformedURLException) {
            return BadURL;
        }
        if (e instanceof SSLException) {
            return SecureConnectionFailed;
        }
        if ((e instanceof HttpResponseException) || (e instanceof ClientProtocolException)) {
            return BadServerResponse;
        }
        return error;
    }

    public static int exceptionToErrorCode(Exception e) {
        return exceptionToNetworkFailure(e).getErrorCode();
    }

    public static NetworkFailure fromErrorCode(int errorCode) {
        log.debug("fromErrorCode invoked with errorCode: " + errorCode);
        for (NetworkFailure failure : values()) {
            if (failure.getErrorCode() == errorCode) {
                log.debug("fromErrorCode found matching failure: " + failure);
                return failure;
            }
        }
        return Unknown;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
