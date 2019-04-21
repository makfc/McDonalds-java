package com.google.api.client.http.apache;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.util.Preconditions;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

final class ApacheHttpRequest extends LowLevelHttpRequest {
    private final HttpClient httpClient;
    private final HttpRequestBase request;

    ApacheHttpRequest(HttpClient httpClient, HttpRequestBase request) {
        this.httpClient = httpClient;
        this.request = request;
    }

    public void addHeader(String name, String value) {
        this.request.addHeader(name, value);
    }

    public void setTimeout(int connectTimeout, int readTimeout) throws IOException {
        HttpParams params = this.request.getParams();
        ConnManagerParams.setTimeout(params, (long) connectTimeout);
        HttpConnectionParams.setConnectionTimeout(params, connectTimeout);
        HttpConnectionParams.setSoTimeout(params, readTimeout);
    }

    public LowLevelHttpResponse execute() throws IOException {
        if (getStreamingContent() != null) {
            Preconditions.checkArgument(this.request instanceof HttpEntityEnclosingRequest, "Apache HTTP client does not support %s requests with content.", this.request.getRequestLine().getMethod());
            ContentEntity entity = new ContentEntity(getContentLength(), getStreamingContent());
            entity.setContentEncoding(getContentEncoding());
            entity.setContentType(getContentType());
            ((HttpEntityEnclosingRequest) this.request).setEntity(entity);
        }
        HttpRequestBase httpRequestBase = this.request;
        HttpClient httpClient = this.httpClient;
        HttpRequestBase httpRequestBase2 = this.request;
        return new ApacheHttpResponse(httpRequestBase, !(httpClient instanceof HttpClient) ? httpClient.execute(httpRequestBase2) : HttpInstrumentation.execute(httpClient, httpRequestBase2));
    }
}
