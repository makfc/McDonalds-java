package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.newrelic.agent.android.harvest.type.Harvestable;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.stats.TicToc;
import com.newrelic.agent.android.util.ExceptionHelper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public class HarvestConnection implements HarvestErrorCodes {
    private static final String APPLICATION_TOKEN_HEADER = "X-App-License-Key";
    private static final String COLLECTOR_CONNECT_URI = "/mobile/v3/connect";
    private static final String COLLECTOR_DATA_URI = "/mobile/v3/data";
    private static final String CONNECT_TIME_HEADER = "X-NewRelic-Connect-Time";
    private static final Boolean DISABLE_COMPRESSION_FOR_DEBUGGING = Boolean.valueOf(false);
    private String applicationToken;
    private final HttpClient collectorClient;
    private String collectorHost;
    private ConnectInformation connectInformation;
    private final AgentLog log = AgentLogManager.getAgentLog();
    private long serverTimestamp;
    private boolean useSsl;

    public HarvestConnection() {
        int CONNECTION_TIMEOUT = (int) TimeUnit.MILLISECONDS.convert(20, TimeUnit.SECONDS);
        BasicHttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, CONNECTION_TIMEOUT);
        HttpConnectionParams.setTcpNoDelay(params, true);
        HttpConnectionParams.setSocketBufferSize(params, 8192);
        this.collectorClient = new DefaultHttpClient(params);
    }

    public HttpPost createPost(String uri, String message) {
        String contentEncoding = (message.length() <= 512 || DISABLE_COMPRESSION_FOR_DEBUGGING.booleanValue()) ? "identity" : "deflate";
        HttpPost post = new HttpPost(uri);
        post.addHeader(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/json");
        post.addHeader("Content-Encoding", contentEncoding);
        post.addHeader("User-Agent", System.getProperty("http.agent"));
        if (this.applicationToken == null) {
            this.log.error("Cannot create POST without an Application Token.");
            return null;
        }
        post.addHeader(APPLICATION_TOKEN_HEADER, this.applicationToken);
        if (this.serverTimestamp != 0) {
            post.addHeader(CONNECT_TIME_HEADER, Long.valueOf(this.serverTimestamp).toString());
        }
        if ("deflate".equals(contentEncoding)) {
            post.setEntity(new ByteArrayEntity(deflate(message)));
            return post;
        }
        try {
            post.setEntity(new StringEntity(message, "utf-8"));
            return post;
        } catch (UnsupportedEncodingException e) {
            this.log.error("UTF-8 is unsupported");
            throw new IllegalArgumentException(e);
        }
    }

    public HarvestResponse send(HttpPost post) {
        HarvestResponse harvestResponse = new HarvestResponse();
        try {
            TicToc timer = new TicToc();
            timer.tic();
            HttpResponse response = this.collectorClient.execute(post);
            harvestResponse.setResponseTime(timer.toc());
            harvestResponse.setStatusCode(response.getStatusLine().getStatusCode());
            try {
                harvestResponse.setResponseBody(readResponse(response));
                return harvestResponse;
            } catch (IOException e) {
                e.printStackTrace();
                this.log.error("Failed to retrieve collector response: " + e.getMessage());
                return harvestResponse;
            }
        } catch (Exception e2) {
            this.log.error("Failed to send POST to collector: " + e2.getMessage());
            recordCollectorError(e2);
            return null;
        }
    }

    public HarvestResponse sendConnect() {
        if (this.connectInformation == null) {
            throw new IllegalArgumentException();
        }
        HttpPost connectPost = createConnectPost(this.connectInformation.toJsonString());
        if (connectPost == null) {
            this.log.error("Failed to create connect POST");
            return null;
        }
        TicToc timer = new TicToc();
        timer.tic();
        HarvestResponse response = send(connectPost);
        StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Connect", timer.toc());
        return response;
    }

    public HarvestResponse sendData(Harvestable harvestable) {
        if (harvestable == null) {
            throw new IllegalArgumentException();
        }
        HttpPost dataPost = createDataPost(harvestable.toJsonString());
        if (dataPost != null) {
            return send(dataPost);
        }
        this.log.error("Failed to create data POST");
        return null;
    }

    public HttpPost createConnectPost(String message) {
        return createPost(getCollectorConnectUri(), message);
    }

    public HttpPost createDataPost(String message) {
        return createPost(getCollectorDataUri(), message);
    }

    private byte[] deflate(String message) {
        Deflater deflater = new Deflater();
        deflater.setInput(message.getBytes());
        deflater.finish();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        while (!deflater.finished()) {
            int byteCount = deflater.deflate(buf);
            if (byteCount <= 0) {
                this.log.error("HTTP request contains an incomplete payload");
            }
            baos.write(buf, 0, byteCount);
        }
        deflater.end();
        return baos.toByteArray();
    }

    public static String readResponse(HttpResponse response) throws IOException {
        char[] buf = new char[8192];
        StringBuilder sb = new StringBuilder();
        InputStream in = response.getEntity().getContent();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (true) {
                int n = reader.read(buf);
                if (n < 0) {
                    break;
                }
                sb.append(buf, 0, n);
            }
            return sb.toString();
        } finally {
            in.close();
        }
    }

    private void recordCollectorError(Exception e) {
        this.log.error("HarvestConnection: Attempting to convert network exception " + e.getClass().getName() + " to error code.");
        StatsEngine.get().inc("Supportability/AgentHealth/Collector/ResponseErrorCodes/" + ExceptionHelper.exceptionToErrorCode(e));
    }

    private String getCollectorUri(String resource) {
        return (this.useSsl ? "https://" : "http://") + this.collectorHost + resource;
    }

    private String getCollectorConnectUri() {
        return getCollectorUri(COLLECTOR_CONNECT_URI);
    }

    private String getCollectorDataUri() {
        return getCollectorUri(COLLECTOR_DATA_URI);
    }

    public void setServerTimestamp(long serverTimestamp) {
        this.log.debug("Setting server timestamp: " + serverTimestamp);
        this.serverTimestamp = serverTimestamp;
    }

    public void useSsl(boolean useSsl) {
        this.useSsl = useSsl;
    }

    public void setApplicationToken(String applicationToken) {
        this.applicationToken = applicationToken;
    }

    public void setCollectorHost(String collectorHost) {
        this.collectorHost = collectorHost;
    }

    public void setConnectInformation(ConnectInformation connectInformation) {
        this.connectInformation = connectInformation;
    }

    public ConnectInformation getConnectInformation() {
        return this.connectInformation;
    }
}
