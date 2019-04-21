package com.newrelic.agent.android.crash;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.metric.MetricNames;
import com.newrelic.agent.android.payload.PayloadSender;
import com.newrelic.agent.android.stats.StatsEngine;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CrashSender extends PayloadSender {
    private static final String CONTENT_TYPE = "application/json";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String CRASH_COLLECTOR_PATH = "/mobile_crash";
    public static final int CRASH_COLLECTOR_TIMEOUT = 5000;
    private final Crash crash;

    public CrashSender(Crash crash, AgentConfiguration agentConfiguration) {
        super(crash.toJsonString().getBytes(), agentConfiguration);
        this.crash = crash;
    }

    /* Access modifiers changed, original: protected */
    public HttpURLConnection getConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(getProtocol() + this.agentConfiguration.getCrashCollectorHost() + CRASH_COLLECTOR_PATH).openConnection();
        connection.setDoOutput(true);
        connection.setChunkedStreamingMode(0);
        connection.setRequestProperty("Content-Type", CONTENT_TYPE);
        connection.setRequestProperty(this.agentConfiguration.getAppTokenHeader(), this.agentConfiguration.getApplicationToken());
        connection.setRequestProperty(this.agentConfiguration.getDeviceOsNameHeader(), Agent.getDeviceInformation().getOsName());
        connection.setRequestProperty(this.agentConfiguration.getAppVersionHeader(), Agent.getApplicationInformation().getAppVersion());
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        return connection;
    }

    public PayloadSender call() throws Exception {
        setPayload(this.crash.toJsonString().getBytes());
        this.crash.incrementUploadCount();
        this.agentConfiguration.getCrashStore().store(this.crash);
        try {
            return super.call();
        } catch (Exception e) {
            onFailedUpload("Unable to report crash to New Relic, will try again later. " + e);
            return this;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onRequestResponse(HttpURLConnection connection) throws IOException {
        switch (connection.getResponseCode()) {
            case 200:
                StatsEngine.get().sampleTimeMs(MetricNames.SUPPORTABILITY_CRASH_UPLOAD_TIME, this.timer.peek());
                log.info("Crash " + this.crash.getUuid().toString() + " successfully submitted.");
                break;
            case VTMCDataCache.MAXSIZE /*500*/:
                StatsEngine.get().inc(MetricNames.SUPPORTABILITY_CRASH_REMOVED_REJECTED);
                onFailedUpload("The crash was rejected and will be deleted - Response code " + connection.getResponseCode());
                break;
            default:
                onFailedUpload("Something went wrong while submitting a crash (will try again later) - Response code " + connection.getResponseCode());
                break;
        }
        log.debug("Crash collection took " + this.timer.toc() + "ms");
    }

    /* Access modifiers changed, original: protected */
    public void onFailedUpload(String errorMsg) {
        log.error(errorMsg);
        StatsEngine.get().inc(MetricNames.SUPPORTABILITY_CRASH_FAILED_UPLOAD);
    }

    /* Access modifiers changed, original: protected */
    public void onRequestException(Exception e) {
        log.error("Crash upload failed: " + e);
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldUploadOpportunistically() {
        return Agent.hasReachableNetworkConnection(null);
    }
}
