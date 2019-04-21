package com.newrelic.agent.android.agentdata;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.metric.MetricNames;
import com.newrelic.agent.android.payload.Payload;
import com.newrelic.agent.android.payload.PayloadController;
import com.newrelic.agent.android.payload.PayloadSender;
import com.newrelic.agent.android.stats.StatsEngine;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AgentDataSender extends PayloadSender {
    private static final String CONTENT_TYPE = "application/octet-stream";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    public AgentDataSender(byte[] bytes, AgentConfiguration agentConfiguration) {
        super(bytes, agentConfiguration);
    }

    public AgentDataSender(Payload payload, AgentConfiguration agentConfiguration) {
        super(payload, agentConfiguration);
    }

    /* Access modifiers changed, original: protected */
    public HttpURLConnection getConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(getProtocol() + this.agentConfiguration.getHexCollectorHost() + this.agentConfiguration.getHexCollectorPath()).openConnection();
        connection.setDoOutput(true);
        connection.setChunkedStreamingMode(0);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", CONTENT_TYPE);
        connection.setRequestProperty(this.agentConfiguration.getAppTokenHeader(), this.agentConfiguration.getApplicationToken());
        connection.setRequestProperty(this.agentConfiguration.getDeviceOsNameHeader(), Agent.getDeviceInformation().getOsName());
        connection.setRequestProperty(this.agentConfiguration.getAppVersionHeader(), Agent.getApplicationInformation().getAppVersion());
        connection.setConnectTimeout(this.agentConfiguration.getHexCollectorTimeout());
        connection.setReadTimeout(this.agentConfiguration.getHexCollectorTimeout());
        return connection;
    }

    /* Access modifiers changed, original: protected */
    public void onRequestResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        switch (responseCode) {
            case 200:
                StatsEngine.get().sampleTimeMs(MetricNames.SUPPORTABILITY_HEX_UPLOAD_TIME, this.timer.peek());
                break;
            case VTMCDataCache.MAXSIZE /*500*/:
                onFailedUpload("The data payload was rejected and will be deleted - Response code " + responseCode);
                StatsEngine.get().sampleTimeMs(MetricNames.SUPPORTABILITY_HEX_FAILED_UPLOAD, this.timer.peek());
                break;
            default:
                onFailedUpload("Something went wrong while submitting the data payload (will try again later) - Response code " + responseCode);
                break;
        }
        log.debug("Handled Exception collection took " + this.timer.toc() + "ms");
    }

    /* Access modifiers changed, original: protected */
    public void onFailedUpload(String errorMsg) {
        log.error(errorMsg);
        StatsEngine.get().inc(MetricNames.SUPPORTABILITY_HEX_FAILED_UPLOAD);
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldUploadOpportunistically() {
        return PayloadController.shouldUploadOpportunistically();
    }
}
