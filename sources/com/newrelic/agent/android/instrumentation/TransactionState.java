package com.newrelic.agent.android.instrumentation;

import com.facebook.internal.NativeProtocol;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.producer.HttpErrorMeasurementProducer;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.Util;
import java.net.MalformedURLException;
import java.net.URL;

public final class TransactionState {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private String appData;
    private long bytesReceived;
    private long bytesSent;
    private String carrier = "unknown";
    private String contentType;
    private long endTime;
    private int errorCode;
    private String httpMethod;
    private long startTime = System.currentTimeMillis();
    private State state = State.READY;
    private int statusCode;
    private TransactionData transactionData;
    private String url;
    private String wanType = "unknown";

    private enum State {
        READY,
        SENT,
        COMPLETE
    }

    public TransactionState() {
        TraceMachine.enterNetworkSegment("External/unknownhost");
    }

    public void setCarrier(String carrier) {
        if (isSent()) {
            log.warning("setCarrier(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.carrier = carrier;
        TraceMachine.setCurrentTraceParam(AnalyticAttribute.CARRIER_ATTRIBUTE, carrier);
    }

    public void setWanType(String wanType) {
        if (isSent()) {
            log.warning("setWanType(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.wanType = wanType;
        TraceMachine.setCurrentTraceParam(HttpErrorMeasurementProducer.WAN_TYPE_PARAMS_KEY, wanType);
    }

    public void setAppData(String appData) {
        if (isComplete()) {
            log.warning("setAppData(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.appData = appData;
        TraceMachine.setCurrentTraceParam("encoded_app_data", appData);
    }

    public void setUrl(String urlString) {
        String url = Util.sanitizeUrl(urlString);
        if (url != null) {
            if (isSent()) {
                log.warning("setUrl(...) called on TransactionState in " + this.state.toString() + " state");
                return;
            }
            this.url = url;
            try {
                TraceMachine.setCurrentDisplayName("External/" + new URL(url).getHost());
            } catch (MalformedURLException e) {
                log.error("unable to parse host name from " + url);
            }
            TraceMachine.setCurrentTraceParam("uri", url);
        }
    }

    public void setHttpMethod(String httpMethod) {
        if (isSent()) {
            log.warning("setHttpMethod(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.httpMethod = httpMethod;
        TraceMachine.setCurrentTraceParam(HttpErrorMeasurementProducer.HTTP_METHOD_PARAMS_KEY, httpMethod);
    }

    public String getUrl() {
        return this.url;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public boolean isSent() {
        return this.state.ordinal() >= State.SENT.ordinal();
    }

    public boolean isComplete() {
        return this.state.ordinal() >= State.COMPLETE.ordinal();
    }

    public void setStatusCode(int statusCode) {
        if (isComplete()) {
            log.warning("setStatusCode(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.statusCode = statusCode;
        TraceMachine.setCurrentTraceParam("status_code", Integer.valueOf(statusCode));
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setErrorCode(int errorCode) {
        if (isComplete()) {
            if (this.transactionData != null) {
                this.transactionData.setErrorCode(errorCode);
            }
            log.warning("setErrorCode(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.errorCode = errorCode;
        TraceMachine.setCurrentTraceParam(NativeProtocol.BRIDGE_ARG_ERROR_CODE, Integer.valueOf(errorCode));
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setBytesSent(long bytesSent) {
        if (isComplete()) {
            log.warning("setBytesSent(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.bytesSent = bytesSent;
        TraceMachine.setCurrentTraceParam("bytes_sent", Long.valueOf(bytesSent));
        this.state = State.SENT;
    }

    public void setBytesReceived(long bytesReceived) {
        if (isComplete()) {
            log.warning("setBytesReceived(...) called on TransactionState in " + this.state.toString() + " state");
            return;
        }
        this.bytesReceived = bytesReceived;
        TraceMachine.setCurrentTraceParam("bytes_received", Long.valueOf(bytesReceived));
    }

    public long getBytesReceived() {
        return this.bytesReceived;
    }

    public TransactionData end() {
        if (!isComplete()) {
            this.state = State.COMPLETE;
            this.endTime = System.currentTimeMillis();
            TraceMachine.exitMethod();
        }
        return toTransactionData();
    }

    private TransactionData toTransactionData() {
        if (!isComplete()) {
            log.warning("toTransactionData() called on incomplete TransactionState");
        }
        if (this.url == null) {
            log.error("Attempted to convert a TransactionState instance with no URL into a TransactionData");
            return null;
        }
        if (this.transactionData == null) {
            this.transactionData = new TransactionData(this.url, this.httpMethod, this.carrier, ((float) (this.endTime - this.startTime)) / 1000.0f, this.statusCode, this.errorCode, this.bytesSent, this.bytesReceived, this.appData, this.wanType);
        }
        return this.transactionData;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String toString() {
        return "TransactionState{url='" + this.url + '\'' + ", httpMethod='" + this.httpMethod + '\'' + ", statusCode=" + this.statusCode + ", errorCode=" + this.errorCode + ", bytesSent=" + this.bytesSent + ", bytesReceived=" + this.bytesReceived + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", appData='" + this.appData + '\'' + ", carrier='" + this.carrier + '\'' + ", wanType='" + this.wanType + '\'' + ", state=" + this.state + ", contentType='" + this.contentType + '\'' + ", transactionData=" + this.transactionData + '}';
    }
}
