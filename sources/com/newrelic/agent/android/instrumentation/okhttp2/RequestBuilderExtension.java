package com.newrelic.agent.android.instrumentation.okhttp2;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import java.net.URL;

public class RequestBuilderExtension extends Builder {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private Builder impl;
    private TransactionState transactionState;

    public RequestBuilderExtension(Builder impl) {
        this.impl = impl;
        setCrossProcessHeader();
    }

    public Builder url(String url) {
        return this.impl.url(url);
    }

    public Builder url(URL url) {
        return this.impl.url(url);
    }

    public Builder header(String name, String value) {
        return this.impl.header(name, value);
    }

    public Builder addHeader(String name, String value) {
        return this.impl.addHeader(name, value);
    }

    public Builder removeHeader(String name) {
        return this.impl.removeHeader(name);
    }

    public Builder headers(Headers headers) {
        return this.impl.headers(headers);
    }

    public Builder cacheControl(CacheControl cacheControl) {
        return this.impl.cacheControl(cacheControl);
    }

    public Builder get() {
        return this.impl.get();
    }

    public Builder head() {
        return this.impl.head();
    }

    public Builder post(RequestBody body) {
        return this.impl.post(body);
    }

    public Builder delete() {
        return this.impl.delete();
    }

    public Builder put(RequestBody body) {
        return this.impl.put(body);
    }

    public Builder patch(RequestBody body) {
        return this.impl.patch(body);
    }

    public Builder method(String method, RequestBody body) {
        return this.impl.method(method, body);
    }

    public Builder tag(Object tag) {
        return this.impl.tag(tag);
    }

    public Request build() {
        return this.impl.build();
    }

    private void setCrossProcessHeader() {
        String crossProcessId = Agent.getCrossProcessId();
        if (crossProcessId != null) {
            this.impl.removeHeader(TransactionStateUtil.CROSS_PROCESS_ID_HEADER);
            this.impl.addHeader(TransactionStateUtil.CROSS_PROCESS_ID_HEADER, crossProcessId);
        }
    }
}
