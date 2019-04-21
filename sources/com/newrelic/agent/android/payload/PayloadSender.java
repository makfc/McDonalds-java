package com.newrelic.agent.android.payload;

import com.autonavi.amap.mapcore.VTMCDataCache;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.TicToc;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.Callable;

public abstract class PayloadSender implements Callable<PayloadSender> {
    protected static final AgentLog log = AgentLogManager.getAgentLog();
    protected final AgentConfiguration agentConfiguration;
    protected Payload payload;
    protected int responseCode;
    protected final TicToc timer;

    public interface CompletionHandler {
        void onException(PayloadSender payloadSender, Exception exception);

        void onResponse(PayloadSender payloadSender);
    }

    public abstract HttpURLConnection getConnection() throws IOException;

    public PayloadSender(AgentConfiguration agentConfiguration) {
        this.agentConfiguration = agentConfiguration;
        this.timer = new TicToc();
        this.responseCode = 0;
    }

    public PayloadSender(Payload payload, AgentConfiguration agentConfiguration) {
        this(agentConfiguration);
        this.payload = payload;
    }

    public PayloadSender(byte[] payloadBytes, AgentConfiguration agentConfiguration) {
        this(agentConfiguration);
        this.payload = new Payload(payloadBytes);
    }

    public Payload getPayload() {
        return this.payload;
    }

    public void setPayload(byte[] payloadBytes) {
        this.payload.putBytes(payloadBytes);
    }

    /* Access modifiers changed, original: protected */
    public void onRequestResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        switch (responseCode) {
            case 200:
                InputStream responseInputStream = connection.getInputStream();
                if (responseInputStream != null) {
                    onRequestContent(readStream(responseInputStream, responseInputStream.available()));
                    break;
                }
                break;
            case VTMCDataCache.MAXSIZE /*500*/:
                onFailedUpload("The data payload was rejected and will be deleted - Response code " + responseCode);
                break;
            default:
                onFailedUpload("Something went wrong while submitting the data payload (will try again later) - Response code " + responseCode);
                break;
        }
        log.debug("Payload delivery took " + this.timer.toc() + "ms");
    }

    /* Access modifiers changed, original: protected */
    public void onRequestContent(String responseString) {
    }

    /* Access modifiers changed, original: protected */
    public void onRequestException(Exception e) {
        onFailedUpload("Data upload failed: " + e);
    }

    /* Access modifiers changed, original: protected */
    public void onFailedUpload(String errorMsg) {
        log.error(errorMsg);
    }

    public PayloadSender call() throws Exception {
        try {
            this.timer.tic();
            HttpURLConnection connection = getConnection();
            OutputStream out;
            try {
                connection.connect();
                out = new BufferedOutputStream(connection.getOutputStream());
                out.write(this.payload.getBytes());
                out.close();
                this.responseCode = connection.getResponseCode();
                onRequestResponse(connection);
                connection.disconnect();
            } catch (Exception e) {
                try {
                    onRequestException(e);
                } finally {
                    connection.disconnect();
                }
            } catch (Throwable th) {
                out.close();
            }
        } catch (Exception e2) {
            onFailedUpload("Unable to upload data to New Relic, will try again later. " + e2);
        }
        return this;
    }

    /* Access modifiers changed, original: protected */
    public String getProtocol() {
        return this.agentConfiguration.useSsl() ? "https://" : "http://";
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    /* Access modifiers changed, original: protected */
    public String readStream(InputStream stream, int maxLength) throws IOException {
        InputStreamReader reader = new InputStreamReader(stream, Utf8Charset.NAME);
        char[] buffer = new char[maxLength];
        int numChars = 0;
        int readSize = 0;
        while (numChars < maxLength && readSize != -1) {
            numChars += readSize;
            readSize = reader.read(buffer, numChars, buffer.length - numChars);
        }
        if (numChars != -1) {
            return new String(buffer, 0, Math.min(numChars, maxLength));
        }
        return null;
    }

    public boolean isSuccessfulResponse() {
        switch (this.responseCode) {
            case 200:
            case VTMCDataCache.MAXSIZE /*500*/:
                return true;
            default:
                return false;
        }
    }

    public boolean equals(Object object) {
        if (object != null && (object instanceof PayloadSender) && getPayload() == ((PayloadSender) object).getPayload()) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldUploadOpportunistically() {
        return Agent.hasReachableNetworkConnection(null);
    }

    public boolean shouldRetry() {
        return false;
    }
}
