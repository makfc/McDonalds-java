package com.newrelic.agent.android.measurement.http;

import com.newrelic.agent.android.measurement.BaseMeasurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.Util;

public class HttpTransactionMeasurement extends BaseMeasurement {
    private String appData;
    private long bytesReceived;
    private long bytesSent;
    private int errorCode;
    private String httpMethod;
    private int statusCode;
    private double totalTime;
    private String url;

    public HttpTransactionMeasurement(String url, String httpMethod, int statusCode, long startTime, double totalTime, long bytesSent, long bytesReceived, String appData) {
        super(MeasurementType.Network);
        url = Util.sanitizeUrl(url);
        setName(url);
        setScope(TraceMachine.getCurrentScope());
        setStartTime(startTime);
        setEndTime(((long) ((int) totalTime)) + startTime);
        setExclusiveTime((long) ((int) (1000.0d * totalTime)));
        this.url = url;
        this.httpMethod = httpMethod;
        this.statusCode = statusCode;
        this.bytesSent = bytesSent;
        this.bytesReceived = bytesReceived;
        this.totalTime = totalTime;
        this.appData = appData;
    }

    public HttpTransactionMeasurement(String url, String httpMethod, int statusCode, int errorCode, long startTime, double totalTime, long bytesSent, long bytesReceived, String appData) {
        this(url, httpMethod, statusCode, startTime, totalTime, bytesSent, bytesReceived, appData);
        this.errorCode = errorCode;
    }

    public double asDouble() {
        return this.totalTime;
    }

    public String getUrl() {
        return this.url;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public double getTotalTime() {
        return this.totalTime;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public long getBytesSent() {
        return this.bytesSent;
    }

    public long getBytesReceived() {
        return this.bytesReceived;
    }

    public String getAppData() {
        return this.appData;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return "HttpTransactionMeasurement{url='" + this.url + '\'' + ", httpMethod='" + this.httpMethod + '\'' + ", totalTime=" + this.totalTime + ", statusCode=" + this.statusCode + ", errorCode=" + this.errorCode + ", bytesSent=" + this.bytesSent + ", bytesReceived=" + this.bytesReceived + ", appData='" + this.appData + '\'' + '}';
    }
}
