package com.newrelic.agent.android.api.p052v1;

/* renamed from: com.newrelic.agent.android.api.v1.ConnectionListener */
public interface ConnectionListener {
    void connected(ConnectionEvent connectionEvent);

    void disconnected(ConnectionEvent connectionEvent);
}
